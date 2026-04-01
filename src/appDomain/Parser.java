package appDomain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import implementations.MyStack;

/**
 * XML parser that checks whether an XML document is properly constructed.
 * Invalid lines are collected and printed after parsing is complete.
 */
public class Parser
{
	/**
	 * Stores the name of an opening tag together with the line number
	 * where it originally appeared. This makes it possible to report
	 * unclosed tags later if they remain in the stack.
	 */
	private static class TagEntry
	{
		String tagName;
		int lineNumber;

		public TagEntry(String tagName, int lineNumber)
		{
			this.tagName = tagName;
			this.lineNumber = lineNumber;
		}
	}

	public static void main(String[] args)
	{
		// The XML file path must be supplied when the program is run
		if (args.length == 0)
		{
			System.out.println("Usage: java Parser <xml-file>");
			return;
		}

		try
		{
			parseXML(args[0]);
		}
		catch (IOException e)
		{
			System.out.println("Error reading file.");
		}
	}

	/**
	 * Reads the XML file line by line and checks for structural errors.
	 *
	 * @param filePath the path to the XML file
	 * @throws IOException if the file cannot be opened or read
	 */
	public static void parseXML(String filePath) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		// Uses the custom stack implementation to track opening tags
		MyStack<TagEntry> stack = new MyStack<>();

		// LinkedHashSet keeps insertion order while preventing duplicate line numbers
		LinkedHashSet<Integer> errorLines = new LinkedHashSet<>();

		// Stores all original lines so they can be printed with their line numbers later
		ArrayList<String> allLines = new ArrayList<>();

		// Matches anything that looks like an XML tag
		Pattern tagPattern = Pattern.compile("<[^>]+>");

		String line;
		int lineNumber = 0;

		// Used to check the "one and only one root tag" rule
		int rootCount = 0;

		while ((line = reader.readLine()) != null)
		{
			lineNumber++;
			allLines.add(line);

			Matcher matcher = tagPattern.matcher(line);

			// A single line may contain multiple tags, so each one is checked
			while (matcher.find())
			{
				String tag = matcher.group();

				// Ignore XML processing instructions such as <?xml version="1.0"?>
				if (tag.startsWith("<?"))
				{
					continue;
				}

				// Self-closing tags do not need matching closing tags
				if (tag.endsWith("/>"))
				{
					/*
					 * If a self-closing tag appears while the stack is empty,
					 * it is acting as a top-level element and may count as a root.
					 */
					if (stack.isEmpty())
					{
						rootCount++;
						if (rootCount > 1)
						{
							errorLines.add(lineNumber);
						}
					}
					continue;
				}

				// Closing tag case: </tag>
				if (tag.startsWith("</"))
				{
					String name = extractName(tag);

					// A closing tag with no earlier opening tag is invalid
					if (stack.isEmpty())
					{
						errorLines.add(lineNumber);
					}
					else
					{
						TagEntry top = stack.peek();

						/*
						 * The closing tag must match the most recent opening tag.
						 * If it does not, the XML nesting is broken.
						 */
						if (top.tagName.equals(name))
						{
							stack.pop();
						}
						else
						{
							errorLines.add(lineNumber);
						}
					}
				}
				else
				{
					// Opening tag case: <tag> or <tag attribute="value">
					String name = extractName(tag);

					/*
					 * If the stack is empty, this tag is starting a top-level section.
					 * That means it may be a root element.
					 */
					if (stack.isEmpty())
					{
						rootCount++;
						if (rootCount > 1)
						{
							errorLines.add(lineNumber);
						}
					}

					stack.push(new TagEntry(name, lineNumber));
				}
			}
		}

		reader.close();

		/*
		 * Any tags left in the stack were never properly closed.
		 * Since popping a stack returns them in reverse order, they are first
		 * stored in a temporary list and then added back in forward order.
		 */
		ArrayList<Integer> remaining = new ArrayList<>();

		while (!stack.isEmpty())
		{
			remaining.add(stack.pop().lineNumber);
		}

		for (int i = remaining.size() - 1; i >= 0; i--)
		{
			errorLines.add(remaining.get(i));
		}

		// If no root tag was found at all, the document is invalid
		if (rootCount == 0 && !allLines.isEmpty())
		{
			errorLines.add(1);
		}

		printResults(errorLines, allLines);
	}

	/**
	 * Extracts only the tag name and ignores brackets, slashes, and attributes.
	 *
	 * Examples:
	 * <book> -> book
	 * </book> -> book
	 * <book id="1"> -> book
	 * <book/> -> book
	 *
	 * @param tag the raw XML tag
	 * @return the cleaned tag name
	 */
	private static String extractName(String tag)
	{
		tag = tag.replace("<", "").replace(">", "").replace("/", "").trim();

		// Ignore attributes by taking only the first word in the tag
		int space = tag.indexOf(" ");
		if (space != -1)
		{
			tag = tag.substring(0, space);
		}

		return tag;
	}

	/**
	 * Prints either a success message or the list of invalid lines.
	 *
	 * @param errorLines the line numbers that contain XML construction errors
	 * @param allLines the original file contents
	 */
	private static void printResults(LinkedHashSet<Integer> errorLines, ArrayList<String> allLines)
	{
		if (errorLines.isEmpty())
		{
			System.out.println("XML is valid.");
		}
		else
		{
			System.out.println("Errors found:");

			for (Integer ln : errorLines)
			{
				System.out.println("Line " + ln + ": " + allLines.get(ln - 1));
			}
		}
	}
}