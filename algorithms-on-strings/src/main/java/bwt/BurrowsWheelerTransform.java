package bwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform
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

	String BWT(String text)
	{
		StringBuilder result = new StringBuilder();

		LinkedList<Character> characters = new LinkedList<Character>();
		for (char ch : text.toCharArray())
		{
			characters.add(ch);
		}

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < text.length(); i++)
		{
			list.add(recreateString(characters));
			characters.addLast(characters.removeFirst());
		}

		Collections.sort(list);

		for (String str : list)
		{
			result.append(str.charAt(str.length() - 1));
		}
		return result.toString();
	}

	private String recreateString(List<Character> characters)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Character ch : characters)
		{
			stringBuilder.append(ch);
		}

		return stringBuilder.toString();
	}

	static public void main(String[] args) throws IOException
	{
		new BurrowsWheelerTransform().run();
	}

	public void run() throws IOException
	{
		FastScanner scanner = new FastScanner();
		String text = scanner.next();
		System.out.println(BWT(text));
	}
}
