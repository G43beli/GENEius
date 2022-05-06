package ch.fhnw.mscmi;

public class GeneUtil {

    public static Gene convert(String [] row) {
        Gene result = new Gene();
        result.setTaxId(Integer.parseInt(row[Gene.TAX_ID_IDX]));
        result.setGeneId(Integer.parseInt(row[Gene.GENE_ID_IDX]));
        result.setSymbol(validateValue(row[Gene.SYMBOL_IDX]));
        result.setLocusTag(validateValue(row[Gene.LOCUS_TAG_IDX]));
        result.setSynonyms(validateValue(row[Gene.SYNONYMS_IDX]));
        result.setDbxrefs(validateValue(row[Gene.DBXREFS_IDX]));
        result.setChromosome(validateValue(row[Gene.CHROMOSOME_IDX]));
        result.setMapLocation(validateValue(row[Gene.MAP_LOCATION_IDX]));
        result.setDescription(validateValue(row[Gene.DESCRIPTION_IDX]));
        result.setGeneType(validateValue(row[Gene.TYPE_OF_GENE_IDX]));
        result.setSfna(validateValue(row[Gene.SFNA_IDX]));
        result.setFnfna(validateValue(row[Gene.FNFNA_IDX]));
        result.setNomenclatureStatus(validateValue(row[Gene.NOMENCLATURE_STATUS_IDX]));
        result.setOtherDesignations(validateValue(row[Gene.OTHER_DESIGNATIONS_IDX]));
        result.setModificationDate(validateValue(row[Gene.MODIFICATION_DATE_IDX]));
        result.setFeatureType(validateValue(row[Gene.FEATURE_TYPE_IDX]));
        return result;
    }

    private static String validateValue(String val) {
        if (val.trim().equals("-")) return null;
        return val;
    }
}
