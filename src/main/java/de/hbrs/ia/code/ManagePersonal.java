package de.hbrs.ia.code;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;

import java.util.List;

/**
 * Code lines are commented for suppressing compile errors.
 * Are there any CRUD-operations missing?
 */
public interface ManagePersonal {
    public void createSalesMan( SalesMan record );

    public void addSocialPerformanceRecord(SocialPerformanceRecord record , SalesMan salesMan );
    // Remark: an SocialPerformanceRecord corresponds to part B of a bonus sheet

    public void delSalesMan( SalesMan record);

    public void delSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan);

    public SalesMan readSalesMan( int sid );

    public List<SalesMan> readAllSalesMen();

    public List<SocialPerformanceRecord> readSocialPerformanceRecord( SalesMan salesMan );
    public List<SocialPerformanceRecord> readAllSocialPerformanceRecord();
    // Remark: How do you integrate the year?

}
