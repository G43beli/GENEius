package ch.fhnw.mscmi.geneservice;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "allgenes")
public class Gene {

	@Id
	@Column(name = "gene_id")
	private int geneId;

	@Column(name = "tax_id")
	private int taxId;
	
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "synonyms")
	private String synonyms;
	
	@OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "type_of_gene", referencedColumnName = "id")
	private GeneType geneType;
	
	@Column(name = "locus_tag")
	private String locusTag;
	
	@Column(name = "dbxrefs")
	private String dbxrefs;
	
	@Column(name = "chromosome")
	private String chromosome;
	
	@Column(name = "map_location")
	private String mapLocation;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "sfna")
	private String sfna;
	
	@Column(name = "fnfna")
	private String fnfna;
	
	@Column(name = "nomenclature_status")
	private String nomenclatureStatus;
	
	@Column(name = "other_designations")
	private String otherDesignations;
	
	@Column(name = "feature_type")
	private String featureType;

	public int getGeneId() {
		return geneId;
	}

	public void setGeneId(int geneId) {
		this.geneId = geneId;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
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
