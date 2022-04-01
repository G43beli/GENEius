package ch.fhnw.mscmi;

public class Gene {

    public static int TAX_ID_IDX = 0;
    public static int GENE_ID_IDX = 1;
    public static int SYMBOL_IDX = 2;
    public static int LOCUS_TAG_IDX = 3;
    public static int SYNONYMS_IDX = 4;
    public static int DBXREFS_IDX = 5;
    public static int CHROMOSOME_IDX = 6;
    public static int MAP_LOCATION_IDX = 7;
    public static int DESCRIPTION_IDX = 8;
    public static int TYPE_OF_GENE_IDX = 9;
    public static int SFNA_IDX = 10;
    public static int FNFNA_IDX = 11;
    public static int NOMENCLATURE_STATUS_IDX = 12;
    public static int OTHER_DESIGNATIONS_IDX = 13;
    public static int MODIFICATION_DATE_IDX = 14;
    public static int FEATURE_TYPE_IDX = 15;

    private int geneId;

    private int taxId;

    private String symbol;

    private String synonyms;

    private String geneType;

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

    private String modificationDate;

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

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

    public String getGeneType() {
        return geneType;
    }

    public void setGeneType(String geneType) {
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
