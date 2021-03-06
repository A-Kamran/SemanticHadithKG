package dataAccess;

public class NarratorsDetail {
	
	private Integer narratorId;
	private Integer narratorKey;
	private String narratorName;
	private String kunyat;
	private String IsmShuhra;
	private String nasab;
	private String laqab;
	private String anNishat;
	private String mazhab;
	private String rutba;
	private String tabqa;
	private String deathYear;
	private String birthYear;
	private Integer age;
	private String aqamah;
	private String deathCity;
	private String akhtalatTadlees;
	private String alMawali;
	private String nFirstChar;
	private String wasaf;
	
	public String getnFirstChar() {
		return nFirstChar;
	}
	public void setnFirstChar(String nFirstChar) {
		this.nFirstChar = nFirstChar;
	}
	public Integer getNarratorId() {
		return narratorId;
	}
	public void setNarratorId(Integer narratorId) {
		this.narratorId = narratorId;
	}
	public String getNarratorName() {
		return narratorName;
	}
	public void setNarratorName(String narratorName) {
		this.narratorName = narratorName;
	}
	public String getKunyat() {
		return kunyat;
	}
	public void setKunyat(String kunyat) {
		this.kunyat = kunyat;
	}
	public String getIsmShuhra() {
		return IsmShuhra;
	}
	public void setIsmShuhra(String ismShuhra) {
		IsmShuhra = ismShuhra;
	}
	public String getNasab() {
		return nasab;
	}
	public void setNasab(String nasab) {
		this.nasab = nasab;
	}
	public String getLaqab() {
		return laqab;
	}
	public void setLaqab(String laqab) {
		this.laqab = laqab;
	}
	public String getAnNishat() {
		return anNishat;
	}
	public void setAnNishat(String anNishat) {
		this.anNishat = anNishat;
	}
	public String getMazhab() {
		return mazhab;
	}
	public void setMazhab(String mazhab) {
		this.mazhab = mazhab;
	}
	public String getRutba() {
		return rutba;
	}
	public void setRutba(String rutba) {
		this.rutba = rutba;
	}
	
	public String getTabqa() {
		return tabqa;
	}
	public void setTabqa(String tabqa) {
		this.tabqa = tabqa;
	}

	public String getDeathYear() {
		return deathYear;
	}
	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAqamah() {
		return aqamah;
	}
	public void setAqamah(String aqamah) {
		this.aqamah = aqamah;
	}
	public String getDeathCity() {
		return deathCity;
	}
	public void setDeathCity(String deathCity) {
		this.deathCity = deathCity;
	}
	public String getAkhtalatTadlees() {
		return akhtalatTadlees;
	}
	public void setAkhtalatTadlees(String akhtalatTadlees) {
		this.akhtalatTadlees = akhtalatTadlees;
	}
	public String getAlMawali() {
		return alMawali;
	}
	public void setAlMawali(String alMawali) {
		this.alMawali = alMawali;
	}
	public void setNarratorKey(int key) {
			this.narratorKey = key; 		
	}
	public int getNarratorKey() {
		return narratorKey; 		
}
	public void setWasaf(String wasaf) {
		this.wasaf = wasaf;
	}
	public String getWasaf() 
	{
		return wasaf;
	}


}
