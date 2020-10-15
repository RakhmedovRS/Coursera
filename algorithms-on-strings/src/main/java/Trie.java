import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie
{
	class FastScanner
	{
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner()
		{
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException
		{
			while (!tok.hasMoreElements())
			{
				tok = new StringTokenizer(in.readLine());
			}
			return tok.nextToken();
		}

		int nextInt() throws IOException
		{
			return Integer.parseInt(next());
		}
	}

	List<Map<Character, Integer>> buildTrie(String[] patterns)
	{
		List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();
		Map<Character, Integer> root = new HashMap<Character, Integer>();
		trie.add(root);
		char ch;
		for (String pattern : patterns)
		{
			Map<Character, Integer> current = root;
			for (int i = 0; i < pattern.length(); i++)
			{
				ch = pattern.charAt(i);
				if (current.get(ch) != null)
				{
					current = trie.get(current.get(ch));
				}
				else
				{
					Map<Character, Integer> node = new HashMap<Character, Integer>();
					trie.add(node);
					current.put(ch, trie.size() - 1);
					current = node;
				}
			}
		}

		return trie;
	}

	static public void main(String[] args) throws IOException
	{
		new Trie().run();
	}

	public void print(List<Map<Character, Integer>> trie)
	{
		for (int i = 0; i < trie.size(); ++i)
		{
			Map<Character, Integer> node = trie.get(i);
			for (Map.Entry<Character, Integer> entry : node.entrySet())
			{
				System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
			}
		}
	}

	public void run() throws IOException
	{
		FastScanner scanner = new FastScanner();
		int patternsCount = scanner.nextInt();
		String[] patterns = new String[patternsCount];
		for (int i = 0; i < patternsCount; ++i)
		{
			patterns[i] = scanner.next();
		}
		List<Map<Character, Integer>> trie = buildTrie(patterns);
		print(trie);
	}
}
