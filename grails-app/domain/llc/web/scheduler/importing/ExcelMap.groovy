package llc.web.scheduler.importing

class ExcelMap {

    static constraints = {
        columnName ( nullable :false, blank:false);
        googleField ( nullable :false, blank:false);
    }
    
    String columnName
    String googleField
    
    public String toString() {
        return "[$columnName : $googleField]"
    }
}
