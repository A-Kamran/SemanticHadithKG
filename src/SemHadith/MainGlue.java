/**
 * 
 */
package SemHadith;

import KG_Generator.KG_Generation;

/**
 * @author Amna
 *
 */
public class MainGlue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// *****************input vocabulary file (Ontology)*****************
		String SOURCE_FILE = "SemanticHadith_v5.owl";
		String workingDir = System.getProperty("user.dir");
		// *****************Path and file to save resulting Graph.*****************
		String OutputFile = workingDir+"/SemanticHadithKG.rdf";
		System.out.println(OutputFile);
		
		KG_Generation ic = new KG_Generation(SOURCE_FILE, OutputFile);
		
		ic.InitializeHadithEngine();
		
		//*****************Connection to DB *****************
		ic.createConnection("hadithFH");
		
		//*****************Creating all instances for all Hadith Collections in DB *****************
		ic.CollectionInstance("02_booksnames");

		
		//*****************Creating Instances for Sahih Bukhari *****************
		ic.BookInstance("csb_bookschapters");
		ic.ChapterInstance("csb_bookssubchapters");
		ic.HNarrator("narratorsdetail");
		ic.HadithInstance("csb_hadith");
		ic.HadithToHadith("csb_hadith");
		
		ic.saveOnt();	
		
		
		
		//***************** Creating Instances for Sahih Muslim *****************
	//	ic.BookInstance("csm_bookschapters");
	//	ic.ChapterInstance("csm_bookssubchapters");
	//	ic.HadithInstance("csm_hadith");
	//	ic.HadithToHadith("csm_hadith");
		
	
		// *****************Creating Instances for Sunan Abi Daud *****************
		//	ic.BookInstance("sad_bookschapters");
		//	ic.ChapterInstance("sad_bookssubchapters");
		//	ic.HadithInstance("sad_hadith");
		//	ic.HadithToHadith("sad_hadith");
		
		
		//*****************Creating Instances for Sunan Ibn Maja	*****************
		//	ic.BookInstance("maj_bookschapters");
		//	ic.ChapterInstance("maj_bookssubchapters");
		//	ic.HadithInstance("maj_hadith");
		//	ic.HadithToHadith("maj_hadith");
		
		//***************** Creating Instances for Sunan Nisai *****************
	//	ic.BookInstance("nis_bookschapters");
		//	ic.ChapterInstance("nis_bookssubchapters");
		//	ic.HadithInstance("nis_hadith");
		//	ic.HadithToHadith("nis_hadith");
		
		// *****************Creating Instances for Tirmidhi*****************
	//	ic.BookInstance("tir_bookschapters");
		//	ic.ChapterInstance("tir_bookssubchapters");
		//	ic.HadithInstance("tir_hadith");
		//	ic.HadithToHadith("tir_hadith");
		
	
	}

}
