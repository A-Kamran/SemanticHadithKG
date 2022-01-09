package SemHadith;

// ***************** imports *****************
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import dataAccess.*;
//import HadithRDFconvertor.*;
import HadithOntology.*;

/**
 * @author Amna
 *
 */
public class InstanceCreation
{

	
	// ***************** @param args*****************
	
	
	public static String SOURCE_FILE;
	public static String OutputFile;
	public static OWLOntology factoryOnt;
	private static OWLOntology owlOntology;
	private static HadithFactory hadithFactory;
	private static OWLOntologyManager manager;
	public static Connection conn = null ;
	public static Statement st = null;
	
	
	public InstanceCreation(String SOURCE_FILE, String OutputFile)
	{
		this.SOURCE_FILE=SOURCE_FILE;
		this.OutputFile = OutputFile;
	}
	
	public static void InitializeHadithEngine() 
	{
		try {
		// mapping of imported Ontologies 
			manager = OWLManager.createOWLOntologyManager();
	
			// Load Ontology From File
			owlOntology = manager.loadOntologyFromOntologyDocument(new 
					FileInputStream(SOURCE_FILE));
			hadithFactory = new HadithFactory(owlOntology);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// ******************* Database Connection*****************
	public static void createConnection(String dbName)
	{

		try {
			conn = connectionFactory.createConnection(dbName);
			st = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConnection()
	{
		try {
			if (conn != null)
			{
				conn.close();
			}
			if (st != null)
{
				st.close();
			}
		} catch (SQLException sqlee)
		{
			sqlee.printStackTrace();
		}
	}
	// ******************* Display the Ontology *****************
	public static void displayIndv()
	{
		Collection Instances=hadithFactory.getAllHadithInstances();
		Iterator itr=Instances.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
	}

	// ******************* Save the Ontology *****************
	public static void saveOnt()
	{
		factoryOnt=hadithFactory.getOwlOntology();
		File fileformated = new File(OutputFile);
		//Get the Ontology format
		OWLOntologyFormat format = manager.getOntologyFormat(factoryOnt);
		OWLXMLOntologyFormat owlxmlFormat = new OWLXMLOntologyFormat();
		if (format.isPrefixOWLOntologyFormat()) { 
			owlxmlFormat.copyPrefixesFrom(format.asPrefixOWLOntologyFormat()); 
		}
		try {
			manager.saveOntology(factoryOnt, owlxmlFormat, IRI.create(fileformated.toURI()));
		} catch (OWLOntologyStorageException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	// ******************* Helping function: to get number of rows in a sql table *****************
	public static int rowCount(String tableName)
	{
		int count =0;
		try 
		{

			ResultSet r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM "+tableName);
			r.next();
			count = r.getInt("rowcount");
			r.close();		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public static HadithCollection collectionInstance;
	// ******************* Create Collection Instances *****************
	public static void CollectionInstance(String collectionTable)
	{
		createConnection("hadithFH");
		int row = rowCount(collectionTable);
		CollectionDataAccess cda = new CollectionDataAccess();

		for(int i = 1; i<=row; i++){

			CollectionData cd = cda.setCollectionAtt(i, conn, st);
			Integer collectionKey = cd.getCollectionID();
			String namePrefix = CollectionName(collectionKey);
			String collectionKeyPadded = "";
			collectionKeyPadded = padding(collectionKey, 2);
			String instanceName = namePrefix+collectionKeyPadded;
			// Create Collection Instance and add its data properties
			collectionInstance = hadithFactory.createHadithCollection(instanceName);
			System.out.println(instanceName);
			//	collectionInstance.addHadithVolumeNo(cd.getVolNo());
			collectionInstance.addName(cd.getCollectionArabName()+"@ar");
			collectionInstance.addName(cd.getCollectionUrduName()+"@ur");
			collectionInstance.addName(cd.getCollectionEngName()+"@en");
			System.out.println(collectionInstance);

		}
		closeConnection();
	}
	
	public static String padding(Integer number, Integer requiredDigits) {
		String paddedString = "";
		int length = (int) (Math.log10(number) + 1);
		if(length<requiredDigits) {
			 paddedString = String.format("%0"+requiredDigits+"d" , number);
			 return paddedString;
		}
		else {
		return number+"";
		}
	}
	public static String CollectionName(Integer i) {
		String prefix = "";
		switch (i)
		{
		case 1:  prefix = "SB"; // Sahih Bukhari 
		break;
		case 2:  prefix = "SM"; // Sahih Muslim
		break;
		case 3:  prefix = "SD"; // Sunan Abi Dauood
		break;
		case 4:  prefix = "SM"; // Sunan Ibn Maja 
		break;
		case 5:  prefix = "SN"; // Sunan Nasai
		break;
		case 6:  prefix = "JT"; // Jam'i Tirmidhi
		break;
		default: prefix = "misc";
		}
		return prefix;
	}
	
	
	// ******************* Create Book Instances *****************
	
	private static HadithBook bookInstance;
	public static void BookInstance(String bookTable){
		createConnection("hadithFH");
		int row = rowCount(bookTable);
		BookDataAccess bda = new BookDataAccess();
		int collection_id;
		if(bookTable.equals("csb_bookschapters"))
			collection_id  = 1;
		else if (bookTable.equals("csm_bookschapters"))
			collection_id  = 2;
		else if (bookTable.equals("sad_bookschapters"))
			collection_id  = 3;
		else if (bookTable.equals("maj_bookschapters"))
			collection_id  = 4;
			else if (bookTable.equals("nis_bookschapters"))
			collection_id  = 5;
		else if (bookTable.equals("tir_bookschapters"))
			collection_id  = 6;
		else
			collection_id  = 1;
		
		String collectionPrefix = CollectionName(collection_id);

		for(int i = 1; i<=row; i++){
			BookData bd = bda.setBookAtt(i, bookTable, conn, st);		
			//Instance Name functionality
			//System.out.println(bd);
			
			//String collectionPrefix = CollectionName(bd.getCollectionID());
			int bookKey = bd.getBookKey();
			
			String bookKeyPadded = padding(bookKey, 2);
			String instanceName = collectionPrefix+"-"+"BK"+bookKeyPadded;
		
			// Create Book Instance and add its data properties
			bookInstance = hadithFactory.createHadithBook(instanceName);
			System.out.println(instanceName);
			bookInstance.addHadithBookNo(bd.getHadithBookNo());
			bookInstance.addSequenceNo(bd.getSequenceNo());
			bookInstance.addName(bd.getBookTitleA()+"@ar");
			bookInstance.addName(bd.getBookTitleA()+"@ur");
			bookInstance.addName(bd.getBookTitleA()+"@en");
			bookInstance.addHadithBookIntro(bd.getHadithBookIntroA()+"@ar");
			bookInstance.addHadithBookIntro(bd.getHadithBookIntroU()+"@ur");
			bookInstance.addHadithBookIntro(bd.getHadithBookIntroE()+"@en");
			
			// Object Type Properties
			
			String collectionName = collectionPrefix+padding(collection_id, 2);
			//String collectionName = collectionPrefix+padding(bd.getCollectionID(), 2);
			
			collectionInstance = hadithFactory.getHadithCollection(collectionName);
			bookInstance.addIsPartOfCollection(collectionInstance);
			//System.out.println(bookInstance);
	
		}
		closeConnection();
	}
	
	
	// ******************* Create Chapter Instances *****************
		private static HadithChapter chapterInstance;
		public static void ChapterInstance(String chapterTable)
		{
			createConnection("hadithFH");	
			int row = rowCount( chapterTable);
			String collection = chapterTable.replaceAll("\\_.*","");
			int collection_id;
			if(chapterTable.equals("csb_bookssubchapters"))
				collection_id  = 1;
			else if (chapterTable.equals("csm_bookssubchapters"))
				collection_id  = 2;
			else if (chapterTable.equals("sad_bookssubchapters"))
				collection_id  = 3;
			else if (chapterTable.equals("maj_bookssubchapters"))
				collection_id  = 4;
				else if (chapterTable.equals("nis_bookssubchapters"))
				collection_id  = 5;
			else if (chapterTable.equals("tir_bookssubchapters"))
				collection_id  = 6;
			else
				collection_id  = 1;
			
			String collectionPrefix = CollectionName(collection_id);
			ChapterDataAccess cda = new ChapterDataAccess();

			for(int i=1; i<=row; i++)
			{
				ChapterData cd = cda.setChapterAtt(i, chapterTable, conn, st);
				
				//Instance Name functionality
				
				int chapKey = cd.getChapKey();
				String chapKeyPadded = padding(chapKey, 4);
				String instanceName = collectionPrefix+"-"+"CH"+chapKeyPadded;
				// Create Chapter Instance and add its data properties
				chapterInstance = hadithFactory.createHadithChapter(instanceName);
				chapterInstance.addHadithChapterNo(cd.getChapterNo());
				chapterInstance.addSequenceNo(cd.getSequenceNo());
				chapterInstance.addName(cd.getChapLabelArab()+"@ar");
				chapterInstance.addName(cd.getChapLabelUrdu()+"@ur");
				chapterInstance.addName(cd.getChapLabelEng()+"@en");
				
				// Object Type Properties
				String bookName =collectionPrefix +"-BK"+padding(cd.getBookId(),2);
				bookInstance = hadithFactory.getHadithBook(bookName);
				chapterInstance.addIsPartOfBook(bookInstance);
				System.out.println(chapterInstance);


			}
//			System.out.println(collection);
			if(collection.equals("csb")  )
			{
				ChapterPreface(collectionPrefix, collection);
			}
			closeConnection();
			
		}
		// ************* Chapter Preface ************
		public static void ChapterPreface(String collectionPrefix, String collection)
		{
			ChapterPrefaceAccess cpa = new ChapterPrefaceAccess();
			
			ChapterPreface cp = cpa.setTarjamah(collection, conn, st);
			for (int j=0; j<cp.gethChapterNo().size(); j++) {
				String chapName = collectionPrefix+"-CH"+padding(cp.gethChapterNo().get(j),4);
				chapterInstance = hadithFactory.getHadithChapter(chapName);
				ArrayList<String> tarjamaA = cp.gettarjamaArab();
				ArrayList<String> tarjamaE = cp.gettarjamaEng();
				ArrayList<String> tarjamaU = cp.gettarjamaUrdu();
				chapterInstance.addChapterPreface(tarjamaA.get(j)+"@ar");
				chapterInstance.addChapterPreface(tarjamaU.get(j)+"@ur");
				if(!(tarjamaE.get(j).equals("0")))
				{	chapterInstance.addChapterPreface(tarjamaE.get(j)+"@en");}
			}
		}

		// ******************* Create Hadith Instances *****************
		//private static Hadith hadithInstance;
		static int nullInMapping = 0;
		public static void HadithInstance(String hadithTable){
			createConnection("hadithFH");
			int row = rowCount(hadithTable);
			Hadith hadithInstance;
			
			String collection = hadithTable.replaceAll("\\_.*","");
			int collection_id;
			if(hadithTable.equals("csb_hadith"))
				collection_id  = 1;
			else if (hadithTable.equals("csm_hadith"))
				collection_id  = 2;
			else if (hadithTable.equals("sad_hadith"))
				collection_id  = 3;
			else if (hadithTable.equals("maj_hadith"))
				collection_id  = 4;
				else if (hadithTable.equals("nis_hadith"))
				collection_id  = 5;
			else if (hadithTable.equals("tir_hadith"))
				collection_id  = 6;
			else
				collection_id  = 1;
			
			String collectionPrefix = CollectionName(collection_id);
			HadithDataAccess hda = new HadithDataAccess();
			//closeConnection();

			for(int i=1; i<=row; i++){
			//	createConnection("hadithFH");
				HadithData hd = hda.setHadithAtt(i, conn, st);
				if(hd.getBookId()!=null)
				{

					//Instance Name functionality
					int hadithKey = hd.getHadithKey();
					String hadithKeyPadded = padding(hadithKey, 4);
					String instanceName = collectionPrefix+"-"+"HD"+hadithKeyPadded;
					// Create Hadith Instance and add its data properties
					hadithInstance = hadithFactory.createHadith(instanceName);
					
					ArrayList<String> raqmList = ExtractRaqm(hd.getFullHadithA());
					NDetailDataAccess nda = new NDetailDataAccess();
					int raqmSize = raqmList.size();
					if(raqmSize!=0)
					{
						for(int j=0; j<raqmSize;j++)
						{
							NarratorsDetail nd = nda.setNarratorAtt(Integer.parseInt(raqmList.get(j)), conn, st);
							HNarrator(Integer.parseInt(raqmList.get(j)),nd,hadithInstance);
						//	HNarratorOccurrence(instanceName,Integer.parseInt(raqmList.get(j)),nd,hadithInstance);
						}
					}
					hadithInstance.addHadithReferenceNo(hd.getHadithRefNo());
					hadithInstance.addSequenceNo(hd.getSequenceNo());
					//clean Arabic text from html tags
					String fullHadith = hd.getFullHadithA().replaceAll("<[^>]*>", " ");
					hadithInstance.addFullHadithText(fullHadith+"@ar");
					hadithInstance.addFullHadithText(hd.getFullHadithU()+"@ur");
					hadithInstance.addFullHadithText(hd.getFullHadithE()+"@en");
					hadithInstance.addHadithType(hd.getHadithType()+"@ar");
					hadithInstance.addHadithURL("http://islamicurdubooks.com/Sahih-Bukhari/Sahih-Bukhari-.php?hadith_number="+hd.getHadithRefNo());

					// Object Type Properties
					String ChapterName = collectionPrefix+"-CH"+padding(hd.getChapterId(),4);
					chapterInstance = hadithFactory.getHadithChapter(ChapterName);
					hadithInstance.addIsPartOfChapter(chapterInstance);
				//	closeConnection();
					System.out.println("refNo:"+hd.getHadithRefNo()+" vol: "+hd.getEngVol()+" book: "+hd.getEngBook()+" hadith: "+hd.getEngNumber());
//					if(hd.getEngBook()!=null)
//					{
//						String narratorEng = getSunnahLinks(hd.getEngVol(),hd.getEngBook(), hd.getEngNumber(), hadithInstance);
//
//						if(narratorEng!=null)
//						{
//							/*hadithInstance.addNarratedBy(hadithFactory.createRootNarrrator("RN"+hadithKeyPadded));
//							hadithFactory.getRootNarrrator("RN"+hadithKeyPadded).addName(narratorEng);
//							hadithFactory.getRootNarrrator("RN"+hadithKeyPadded).addNarrated(hadithInstance);*/
//						} else nullInMapping++;
//					}
					//System.out.println(instanceName);
				}
			}
			//System.out.println("missing narrators Record = "+ numberOfMissingRaqm);
			//System.out.println("shown null in mapping = "+ nullInMapping);
			closeConnection();
		}
		
		//********  Helping Function
		
		public static ArrayList<String> ExtractRaqm(String fullHadith) {
			
			ArrayList<String> narratorTag = new ArrayList();
			ArrayList<String> raqmList = new ArrayList();
			Matcher m = Pattern.compile("\\<a(.*?)\\/a>").matcher(fullHadith);
			while ( m.find() ){
			        if (m.group().length() != 0){
			         // add the chapter-verse group to an arrayList (there can be more than one verse in a Hadith)
			        narratorTag.add(m.group(1));
			        }
			}
		//	System.out.println("size = " +narratorTag.size());
			for(int i=0; i<narratorTag.size(); i++) {
			//	System.out.println(narratorTag.get(i));
				Matcher raqm = Pattern.compile("([0-9]+)").matcher(narratorTag.get(i));
				while(raqm.find()) {
					if(raqm.group().length()!=0) {
						raqmList.add(raqm.group(1));
					}
				}	
			}
			return raqmList;
		}
		
		// ******************* Create Narrator Instances *****************
		public static void HadithRootNarrator(int raqm, NarratorsDetail nd, Hadith hadithInstance){
			String narratorKeyPadded = padding(raqm, 5);
			String instanceName = "RN"+narratorKeyPadded;

			// Create Hadith Instance and add its data properties
			RootNarrator rN = hadithFactory.getRootNarrator(instanceName);
			if(rN==null) {	// Check if Narrator already Exists in ontology

				RootNarrator narratorInstance = hadithFactory.createRootNarrator(instanceName);
				if(nd!=null){
					narratorInstance.addName(nd.getNarratorName()+"@ar");
					narratorInstance.addFirstChar(nd.getnFirstChar());
					narratorInstance.addConcealment(nd.getAkhtalatTadlees());
					narratorInstance.addResidence(nd.getAqamah());
					narratorInstance.addHasMaster(nd.getAlMawali());
					narratorInstance.addOffice(nd.getAnNishat());
					narratorInstance.addDeathPlace(nd.getDeathCity());
					narratorInstance.addPopularName(nd.getIsmShuhra());
					narratorInstance.addGeneration(nd.getTabqa());
					narratorInstance.addLineage(nd.getNasab());
					narratorInstance.addTitle(nd.getLaqab());
					narratorInstance.addAge(nd.getAge());
					narratorInstance.addSchoolOfThought(nd.getMazhab());
					narratorInstance.addTeknonym(nd.getKunyat());
					narratorInstance.addBirthYear(nd.getBirthYear());
					narratorInstance.addDeathYear(nd.getDeathYear());
					narratorInstance.addNarratorID(nd.getNarratorId());
					// Object Type Property
					//narratorInstance.addNarrated(hadithInstance);
					//	System.out.println("Root narrator created");
					//	System.out.println(narratorInstance);
				}
			}
			// Object Type Properties
			else{
				//rN.addNarrated(hadithInstance);
			}

		}

		 // ******************* Create Narrator Instances *****************
		static int numberOfMissingRaqm =0;
		public static void HNarrator(int raqm, NarratorsDetail nd, Hadith hadithInstance){
			String narratorKeyPadded = padding(raqm, 5);
			String instanceName = "HN"+narratorKeyPadded;

			// Create Hadith Instance and add its data properties
			HadithNarrator rN = hadithFactory.getHadithNarrator(instanceName);
			if(rN==null)
			{ 
				// Check if Narrator already Exists in ontology

				HadithNarrator narratorInstance1 =	hadithFactory.createHadithNarrator(instanceName);
				if(nd!=null){

					narratorInstance1.addName(nd.getNarratorName()+"@ar");
					narratorInstance1.addFirstChar(nd.getnFirstChar());
					narratorInstance1.addConcealment(nd.getAkhtalatTadlees());
					narratorInstance1.addResidence(nd.getAqamah());
					narratorInstance1.addHasMaster(nd.getAlMawali());
					narratorInstance1.addOffice(nd.getAnNishat());
					narratorInstance1.addDeathPlace(nd.getDeathCity());
					narratorInstance1.addPopularName(nd.getIsmShuhra());
					narratorInstance1.addGeneration(nd.getTabqa());
					narratorInstance1.addLineage(nd.getNasab());
					narratorInstance1.addTitle(nd.getLaqab());
					narratorInstance1.addAge(nd.getAge());
					narratorInstance1.addSchoolOfThought(nd.getMazhab());
					narratorInstance1.addTeknonym(nd.getKunyat());
					narratorInstance1.addBirthYear(nd.getBirthYear());
					narratorInstance1.addDeathYear(nd.getDeathYear());
					narratorInstance1.addNarratorID(nd.getNarratorId());
	
				}
				else{
					narratorInstance1.addNarratorID(raqm);
					numberOfMissingRaqm++;
				}
				
				
				
				// Object Type Property
			//	narratorInstance1.addNarrated(hadithInstance);
				hadithInstance.addHasNarrator(narratorInstance1);

			}
			// Object Type Properties
			else{
			//	rN.addNarrated(hadithInstance);
			}

		}

		
		public static String getSunnahLinks(int volID, int bookId, int number, Hadith hadithInstance) {
			createConnection("sunnah");
			SunnahdotcomAccess sda = new SunnahdotcomAccess();
			Sunnahdotcom sd = sda.setAtt(volID, bookId, number, conn, st);
			if(sd.getLink()!=null){
			hadithInstance.addOwl_sameAs(sd.getLink());
			}
			// else System.out.println("no data returned");
			if(sd.getstartVerse()!=null)
			{
			verseInstance(sd.getstartVerse(), sd.getEndVerse(), sd.getChapterIndex(), hadithInstance);	
			}
			closeConnection();
			return sd.getNarratorEnglish();
		}
		// ******************* Create Verse Instances *****************
		public static void verseInstance(
				ArrayList<Integer> startVerse,ArrayList<Integer> endVerse,
				ArrayList<Integer> chapIndex, Hadith hadithInstance)
		{
			int numOfVerses = endVerse.size();
			if(numOfVerses!=0){
				if(numOfVerses>1){ 	
					for(int j = 0; j<numOfVerses; j++){
						Verse verseInstance = hadithFactory.createVerse("V-"+chapIndex.get(j)+startVerse.get(j));	
						verseInstance.addVerseNo(startVerse.get(j));
						verseInstance.addChapterNo(chapIndex.get(j));
						hadithInstance.addContainsMentionOf(verseInstance);
						verseInstance.addMentionedIn(hadithInstance);
					}
				}
				else{
					Verse verseInstance = hadithFactory.createVerse("V-"+chapIndex.get(0)+startVerse.get(0));	
					verseInstance.addVerseNo(startVerse.get(0));
					verseInstance.addChapterNo(chapIndex.get(0));
					hadithInstance.addContainsMentionOf(verseInstance);
					verseInstance.addMentionedIn(hadithInstance);
				}
			}
		}

		// See-Also Hadith-to-Hadith Links
		public static void HadithToHadith(String hadithTable)
		{
			createConnection("hadithFH");
			int row = rowCount(hadithTable);
			Hadith hadithInstance;
			
			String collection = hadithTable.replaceAll("\\_.*","");
			int collection_id;
			if(hadithTable.equals("csb_hadith"))
				collection_id  = 1;
			else if (hadithTable.equals("csm_hadith"))
				collection_id  = 2;
			else if (hadithTable.equals("sad_hadith"))
				collection_id  = 3;
			else if (hadithTable.equals("maj_hadith"))
				collection_id  = 4;
				else if (hadithTable.equals("nis_hadith"))
				collection_id  = 5;
			else if (hadithTable.equals("tir_hadith"))
				collection_id  = 6;
			else
				collection_id  = 1;
			
			String collectionPrefix = CollectionName(collection_id);
			for(int i=1; i<=row; i++){
				
				HadithDataAccess hda = new HadithDataAccess();
				HadithData hd = hda.setHadithAtt(i, conn, st);
				if(hd.getBookId()!=null && hd.getMukarrarat()!="0"){

					//Instance Name functionality
					int hadithKey = hd.getHadithKey();
					String hadithKeyPadded = padding(hadithKey, 4);
					String instanceName = collectionPrefix+"-"+"HD"+hadithKeyPadded;
					hadithInstance = hadithFactory.getHadith(instanceName);
					String repetitionString = hd.getMukarrarat();
					List<String> list = Arrays.asList(repetitionString.trim().split(","));
					for(int j =0; j<list.size(); j++) {
						Integer hadithRaqm = Integer.parseInt(list.get(j).trim());
						String hadithInstanceName =  padding(hadithRaqm, 4);
						hadithInstanceName = collectionPrefix+"-"+"HD"+hadithInstanceName;
						System.out.println(hadithInstanceName);
						Hadith hadithInstance2 = hadithFactory.getHadith(hadithInstanceName);
						if(hadithInstance2!=null) {
						hadithInstance.addRdf_seeAlso(hadithInstance2);
						}
					}
				}
			}
			closeConnection();
			
		}

		
	  // ******************* Create Matan Instances *****************
		public static void MatanInstance(){
			int row = rowCount("hadith2");
			for(int i = 1; i<=row; i++){
				MatanDataAccess mda = new MatanDataAccess();
				MatanData md = mda.setMatanAtt(i, conn, st);;
				String instanceName = "matan"+i;
				HadithText matanInstance = hadithFactory.createHadithText(instanceName);
		//		matanInstance.addHadithText("See Hadith URL");

				/* not adding actual text due to copyright issue 
				 * matanInstance.addHadithText(md.getHadithTextArab()+"@ar");
				matanInstance.addHadithText(md.getHadithTextEng()+"@en");
				 */
				hadithFactory.getHadith("hadith"+i).addHasPart(matanInstance);
				matanInstance.addIsPartOf(hadithFactory.getHadith("hadith"+i));
			}
		}

		// ******************* Create Sanad Instances *****************
		public static void sanadInstance(){
			int row = rowCount("hadith2");
			for(int i =1; i<=row; i++){
				SanadData sd = SanadDataAccess.setSanadAtt(i, conn, st);;
				String instanceName = "sanad"+i;
				// Create Sanad Instance and add its data properties
				NarratorChain sanadInstance = hadithFactory.createNarratorChain(instanceName);
				//sanadInstance.addNarratorChain("See Hadith URL");
				/* not adding actual text due to copyright issue 
				 * sanadInstance.addNarratorChain(sd.getSanadTextArab()+"@ar");
				sanadInstance.addNarratorChain(sd.getSanadTextEng()+"@en");
				 */
				hadithFactory.getHadith("hadith"+i).addHasPart(sanadInstance);
				sanadInstance.addIsPartOf(hadithFactory.getHadith("hadith"+i));
			}
		}	
	
		
}
