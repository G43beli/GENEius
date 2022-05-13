package ch.fhnw.medicalinformatics;

public class Gene {

	private int geneId;
	
	private String symbol;
	
	private String synonyms;

	private GeneType geneType;
	
	private String locusTag;
	
	private String dbxrefs;
	
	private String chromosome;

	private String mapLocation;
	
	private String description;
	
	private String sfna;
	
	private String fnfna;
	
	private String nomenclatureStatus;
	
	private String otherDesignations;
	
	private String featureType;

	public int getGeneId() {
		return geneId;
	}

	public void setGeneId(int geneId) {
		this.geneId = geneId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	public GeneType getGeneType() {
		return geneType;
	}

	public void setGeneType(GeneType geneType) {
		this.geneType = geneType;
	}

	public String getLocusTag() {
		return locusTag;
	}

	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}

	public String getDbxrefs() {
		return dbxrefs;
	}

	public void setDbxrefs(String dbxrefs) {
		this.dbxrefs = dbxrefs;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSfna() {
		return sfna;
	}

	public void setSfna(String sfna) {
		this.sfna = sfna;
	}

	public String getFnfna() {
		return fnfna;
	}

	public void setFnfna(String fnfna) {
		this.fnfna = fnfna;
	}

	public String getNomenclatureStatus() {
		return nomenclatureStatus;
	}

	public void setNomenclatureStatus(String nomenclatureStatus) {
		this.nomenclatureStatus = nomenclatureStatus;
	}

	public String getOtherDesignations() {
		return otherDesignations;
	}

	public void setOtherDesignations(String otherDesignations) {
		this.otherDesignations = otherDesignations;
	}

	public String getFeatureType() {
		return featureType;
	}

	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
}
