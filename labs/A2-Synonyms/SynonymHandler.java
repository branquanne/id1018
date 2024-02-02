// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
                     // IOException

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(
	        new FileReader(synonymFile));
        int numberOfLines = 0;
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			numberOfLines++;
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[numberOfLines];
        reader = new BufferedReader(new FileReader(synonymFile));
		for (int i = 0; i < numberOfLines; i++)
		    synonymData[i] = reader.readLine();
		reader.close();

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static int synonymLineIndex (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData,
        String word) throws IllegalArgumentException
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data.
	public static String[] addSynonymLine (String[] synonymData,
	    String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}

    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData,
	    String word) throws IllegalArgumentException
	{
		// add code here
        int indexToRemove = synonymLineIndex(synonymData, word);
        

        String[] updatedSynData = new String[synonymData.length - 1]; 
        for (int i = 0, j = 0; i < synonymData.length; i++){
            if (i != indexToRemove){
                updatedSynData[j++] = synonymData[i];
            }
        }
        return updatedSynData;
	}

    // getSynonyms returns synonyms in a given synonym line.
	public static String[] getSynonyms (String synonymLine)
	{
        // add code here
        int delimiterIndex = synonymLine.indexOf('|');
        String synonymsString = synonymLine.substring(delimiterIndex + 1).trim();
        return synonymsString.split(",\\s*");
        /*String[] synonyms = synonymLine.split("//|")[1].split(",");
        for (int i = 0; i < synonyms.length; i++){
            synonyms[i] = synonyms[i].trim();
        }
        return synonyms;*/
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData,
	    String word, String synonym) throws IllegalArgumentException
	{
        // add code here
        int index = synonymLineIndex(synonymData, word);
        String[] synonyms = getSynonyms(synonymData[index]);

        String[] updatedSynonyms = new String[synonyms.length + 1];
        for (int i = 0; i < synonyms.length; i++){
            updatedSynonyms[i] = synonyms[i];
        }
        updatedSynonyms[synonyms.length] = synonym;
        synonymData[index] = word + " | " + String.join(", ", updatedSynonyms);
	}

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
    // If there is only one synonym for the given word, an
    // exception of the type IllegalStateException is thrown.
	public static void removeSynonym(String[] synonymData, String word, String synonym)
        throws IllegalArgumentException, IllegalStateException {
        
        //add code here
        int index = synonymLineIndex(synonymData, word);

        if (index == -1) {
            throw new IllegalArgumentException(word + " not present");
        }

        String synonymLine = synonymData[index];
        String[] synonyms = getSynonyms(synonymLine);

        if (synonyms.length < 2) {
            throw new IllegalStateException("Cannot remove the only synonym for the word");
        }

        String[] updatedSynonyms = new String[synonyms.length - 1];
        boolean found = false;
        for (int i = 0, j = 0; i < synonyms.length; i++) {
            if (!synonyms[i].trim().equals(synonym.trim())) {
                updatedSynonyms[j++] = synonyms[i];
            }
            else{
                found = true;
            }
        }
        if (!found){
            throw new IllegalArgumentException("Not present");
        }

        synonymData[index] = word + " | " + String.join(", ", updatedSynonyms);
}
// sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase(String[] strings) {

        //add code here
        int n = strings.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap
            String temp = strings[minIndex];
            strings[minIndex] = strings[i];
            strings[i] = temp;
        }
    }

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine(String synonymLine) {

        //add code here
        String[] parts = synonymLine.split("\\|", 2); // Split at the first vertical bar
        String initialWord = parts[0].trim();
        String synonymsPart = parts[1].trim();

        String[] synonyms = getSynonyms(synonymsPart);
        sortIgnoreCase(synonyms);

    // Reassemble the sorted synonym line with the initial word
        return initialWord + " | " + String.join(", ", synonyms);
    }

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
    public static void sortSynonymData(String[] synonymData) {

        //add code here
        /*int n = synonymData.length;
    
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
    
            for (int j = i + 1; j < n; j++) {
                if (synonymData[j].compareToIgnoreCase(synonymData[minIndex]) < 0) {
                    minIndex = j;
                }
            }
    
            // Swap
            String temp = synonymData[minIndex];
            synonymData[minIndex] = synonymData[i];
            synonymData[i] = temp;
        }*/
        sortIgnoreCase(synonymData);
    
        // Sort the synonym lines after the swapping is done
        for (int i = 0; i < synonymData.length; i++) {
            synonymData[i] = sortSynonymLine(synonymData[i]);
        }
    }
}