package trieMatching;

import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters = 4;
	public static final int NA = -1;
	public int next[];

	Node()
	{
		next = new int[Letters];
		Arrays.fill(next, NA);
	}
}

class Trie
{
	Trie[] child = new Trie[26];
	boolean end = false;
}

public class TrieMatching implements Runnable
{
	int letterToIndex(char letter)
	{
		switch (letter)
		{
			case 'A':
				return 0;
			case 'C':
				return 1;
			case 'G':
				return 2;
			case 'T':
				return 3;
			default:
				assert (false);
				return Node.NA;
		}
	}

	List<Integer> solve(String text, int n, List<String> patterns)
	{
		Trie root = new Trie();
		for (String pattern : patterns)
		{
			Trie current = root;
			for (char ch : pattern.toCharArray())
			{
				if (current.child[ch - 'A'] == null)
				{
					current.child[ch - 'A'] = new Trie();
				}
				current = current.child[ch - 'A'];
			}
			current.end = true;
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < text.length(); i++)
		{
			Trie current = root;
			for (int j = i; j < text.length(); j++)
			{
				if (current.child[text.charAt(j) - 'A'] == null)
				{
					break;
				}
				current = current.child[text.charAt(j) - 'A'];

				if (current.end)
				{
					result.add(i);
					break;
				}
			}
		}

		return result;
	}

	public void run()
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String text = in.readLine();
			int n = Integer.parseInt(in.readLine());
			List<String> patterns = new ArrayList<String>();
			for (int i = 0; i < n; i++)
			{
				patterns.add(in.readLine());
			}

			List<Integer> ans = solve(text, n, patterns);

			for (int j = 0; j < ans.size(); j++)
			{
				System.out.print("" + ans.get(j));
				System.out.print(j + 1 < ans.size() ? " " : "\n");
			}
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args)
	{
		new Thread(new TrieMatching()).start();
	}
}
