package trie.matching.extended;

import java.io.*;
import java.util.*;

public class TrieMatchingExtended implements Runnable
{
	class Node
	{
		public static final int Letters = 4;
		public static final int NA = -1;
		public int next[];
		public boolean patternEnd;

		Node()
		{
			next = new int[Letters];
			Arrays.fill(next, NA);
			patternEnd = false;
		}
	}

	class Trie
	{
		Trie[] children = new Trie[4];
		boolean end;
	}

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
		int index;
		for (String pattern : patterns)
		{
			Trie current = root;
			for (char ch : pattern.toCharArray())
			{
				index = letterToIndex(ch);
				if (current.children[index] == null)
				{
					current.children[index] = new Trie();
				}
				current = current.children[index];
			}
			current.end = true;
		}

		List<Integer> result = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		outer:
		for (int i = 0; i < text.length(); i++)
		{
			Trie current = root;
			for (int j = i; j < text.length(); j++)
			{
				index = letterToIndex(text.charAt(j));
				if (current.children[index] == null)
				{
					continue outer;
				}
				current = current.children[index];
				if (current.end && set.add(i))
				{
					result.add(i);
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
		new Thread(new TrieMatchingExtended()).start();
	}
}
