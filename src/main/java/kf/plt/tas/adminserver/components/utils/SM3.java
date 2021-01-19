package kf.plt.tas.adminserver.components.utils;

import org.springframework.stereotype.Component;

@Component
public class SM3 {
	long[] T;
	long b32;

	public void initT() {
		T = new long[64];
		for (int i = 0; i < 64; i++)
			T[i] = ((i > 15) ? 0x7a879d8a : 0x79cc4519);
		b32 = 0x00000000FFFFFFFFL;
	}

	public SM3() {
		initT();
	}

	long CROL(long value, long bits) {
		bits = bits % 32; // 超过32位的时候，和没有位移是一样的
		return ((value << bits) | (value >>> (0x20 - bits))) & b32;
	}

	long FF(int j, long x, long y, long z) {
		return ((j > 15) ? ((x & y) | (x & z) | (y & z)) : (x ^ y ^ z) & b32);
	}

	long GG(int j, long x, long y, long z) {
		return ((j > 15) ? ((x & y) | (((~x) & b32) & z)) : (x ^ y ^ z) & b32);
	}

	long P1(long X) {
		return ((X ^ (CROL(X, 15))) ^ (CROL(X, 23)));
	}

	long P0(long X) {
		return (X ^ (CROL(X, 9)) ^ (CROL(X, 17)));
	}

	long[] IVs = { 0x7380166f, 0x4914b2b9, 0x172442d7, 0xda8a0600L,
			0xa96f30bcL, 0x163138aa, 0xe38dee4dL, 0xb0fb0e4eL };

	public byte[] doHash(byte[] data) {
		// since java have no unsigned data types,
		// we are forced to use long instead.
		// that, 32bit mod are required to waste our times.

		long[] IV = new long[8], V = new long[8], W = new long[68], Wp = new long[64];
		System.arraycopy(IVs, 0, V, 0, 8);

		long SS1 = 0, SS2 = 0, TT1 = 0, TT2 = 0;

		// 确定l和k
		int size = data.length;
		long l = size * 8;
		long k = (447 - l % 512) % 512;

		int n = (int) (l + k + 65) / 512;
		// get more data sizes
		// it ensures that the memory is enough
		byte[] rdata = new byte[n * 64];

		System.arraycopy(data, 0, rdata, 0, size);

		rdata[size] = (byte) 0x80;
		// int rdtSize=rdata.length;

		// SET Length
		rdata[n * 64 - 1] = (byte) l;
		rdata[n * 64 - 2] = (byte) (l >> 8);
		rdata[n * 64 - 3] = (byte) (l >> 16);
		rdata[n * 64 - 4] = (byte) (l >> 24);

		long a, b;
		int pointer = 0;

		for (int i = 0; i < n; i++) {
			// Copy 512 bits data, as 64
			for (int j = 0; j < 16; j++)
				W[j] = (((long) (rdata[pointer + j * 4]) << 24) & 0x00000000ff000000L)
						| (((long) (rdata[pointer + j * 4 + 1]) << 16) & 0x0000000000ff0000L)
						| (((long) (rdata[pointer + j * 4 + 2]) << 8) & 0x000000000000ff00L)
						| (((long) (rdata[pointer + j * 4 + 3])) & 0x00000000000000ffL);

			pointer += 64;

			// Init W and Wp
			for (int j = 16; j < 68; j++) {
				W[j] = P1(W[j - 16] ^ W[j - 9] ^ (CROL(W[j - 3], 15)))
						^ (CROL(W[j - 13], 7)) ^ W[j - 6];
			}

			for (int j = 0; j < 64; j++) {
				Wp[j] = W[j] ^ W[j + 4];
			}

			System.arraycopy(V, 0, IV, 0, 8);
			for (int j = 0; j < 64; j++) {
				a = (CROL(IV[0], 12) + IV[4]) & b32;
				b = (a + CROL(T[j], j)) & b32;
				SS1 = CROL(b, 7);
				SS2 = SS1 ^ (CROL(IV[0], 12));

				a = (FF(j, IV[0], IV[1], IV[2]) + IV[3]) & b32;
				b = (a + SS2) & b32;
				TT1 = (b + Wp[j]) & b32;

				a = (GG(j, IV[4], IV[5], IV[6]) + IV[7]) & b32;
				b = (a + SS1) & b32;
				TT2 = (b + W[j]) & b32;

				IV[3] = IV[2];
				IV[2] = CROL(IV[1], 9);
				IV[1] = IV[0];
				IV[0] = TT1;
				IV[7] = IV[6];
				IV[6] = CROL(IV[5], 19);
				IV[5] = IV[4];
				IV[4] = P0(TT2);
			}
			// IV ==>V
			for (int j = 0; j < 8; j++) {
				V[j] ^= IV[j];
			}
		}

		byte[] ret = new byte[32];
		for (int i = 0; i < 8; i++) {
			ret[i * 4] = (byte) (V[i] >> 24);
			ret[i * 4 + 1] = (byte) (V[i] >> 16);
			ret[i * 4 + 2] = (byte) (V[i] >> 8);
			ret[i * 4 + 3] = (byte) (V[i]);
		}

		return ret;
	}

	final char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
			'B', 'C', 'D', 'E', 'F' };

	public String parseCStyleString(byte[] data) {
		String t = "";
		for (int i = 0; i < data.length; i++) {
			int low = (data[i]) & 0x0f;
			int high = ((data[i]) >> 4) & 0x0f;

			t += hex[high];
			t += hex[low];

		}
		return t;
	}
}
