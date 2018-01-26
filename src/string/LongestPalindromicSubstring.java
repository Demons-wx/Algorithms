package string;

/**
 * @author wangxuan
 * @date 2018/1/12 14:50
 */
public class LongestPalindromicSubstring {

    private int lo, maxLen;

    /**
     * 长度为奇数的回文串以最中间的字符的位置为对称轴左右对称，而长度为偶数的回文串对称轴在
     * 中间两个字符之间的间隙。可以利用这种对称性来提高算法效率
     *
     * 我们知道整个字符串中的所有字符，以及字符间的空隙，都可能是某个回文子串的对称轴位置，可以
     * 遍历这些位置，在每个位置上同时向左和向右扩展，直到左右两边的字符不同，或者达到边界。
     *
     * 算法复杂度为： O(n^2)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("abcd"));
    }
}
