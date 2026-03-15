import java.util.ArrayList;
import java.util.List;

class Fancy {
    private List<Long> sequence;
    private long add = 0;
    private long mult = 1;
    private static final int MOD = 1_000_000_007;

    public Fancy() {
        sequence = new ArrayList<>();
    }

    public void append(int val) {
        long valLong = val;
        long inverseMult = modInverse(mult, MOD);
        long storedVal = ((valLong - add + MOD) % MOD * inverseMult) % MOD;
        sequence.add(storedVal);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        add = (add * m) % MOD;
        mult = (mult * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= sequence.size()) {
            return -1;
        }
        long result = (sequence.get(idx) * mult % MOD + add) % MOD;
        return (int) result;
    }

    private long modInverse(long n, int mod) {
        return power(n, mod - 2, mod);
    }

    private long power(long x, int y, int mod) {
        long res = 1;
        x %= mod;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % mod;
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }
}