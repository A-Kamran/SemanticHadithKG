/**
 * 
 */
package KG_Generator;

import HadithOntology.HadithCollection;
import HadithOntology.HadithFactory;
import SemHadith.DB_Manager;
import dataAccess.CollectionData;
import dataAccess.CollectionDataAccess;

/**
 * @author amna
 *
 */
public class collection_Instance {
	



	public static HadithCollection CollectionInstance(String collectionTable, HadithFactory hadithFactory, HadithCollection collectionInstance, java.sql.Connection conn,java.sql.Statement st)
	{
		
		DB_Manager.createConnection("hadithFH");
		
		int row = DB_Manager.rowCount(collectionTable);
		CollectionDataAccess cda = new CollectionDataAccess();

		for(int i = 1; i<=row; i++){

			CollectionData cd = cda.setCollectionAtt(i, conn, st);
			Integer collectionKey = cd.getCollectionID();
			String namePrefix = CollectionName(collectionKey);
			String collectionKeyPadded = "";
			collectionKeyPadded = KG_Generation.padding(collectionKey, 2);
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
		DB_Manager.closeConnection();
		return collectionInstance;
	}
	public static String CollectionName(Integer i) 
	{
		String prefix = "";
		switch (i)
		{
		case 1:  prefix = "SB"; // Sahih Bukhari 
		break;
		case 2:  prefix = "SM"; // Sahih Muslim
		break;
		case 3:  prefix = "SD"; // Sunan Abi Dauood
		break;
		case 4:  prefix = "IM"; // Sunan Ibn Maja 
		break;
		case 5:  prefix = "SN"; // Sunan Nasai
		break;
		case 6:  prefix = "JT"; // Jam'i Tirmidhi
		break;
		default: prefix = "misc";
		}
		return prefix;
	}

}
