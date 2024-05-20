package lk.quantacom.smarterpbackend.dataSeeders;

import lk.quantacom.smarterpbackend.dto.request.RecurringPointOfTimeRequest;

import java.util.ArrayList;
import java.util.List;

public class RecurringPointOfTimeSeeder {

    public static List<RecurringPointOfTimeRequest> Seed(){
        List<RecurringPointOfTimeRequest> seeds = new ArrayList<>();

        seeds.add( SeedFactory("Day of Week", 1, 7, false) );
        seeds.add( SeedFactory("Day of Month", 1, 31, true) );
        seeds.add( SeedFactory("Day of Year", 1, 365, true) );
        seeds.add( SeedFactory("Week of Month", 1, 5, true) );
        seeds.add( SeedFactory("Week of Year", 1, 52, false) );
        seeds.add( SeedFactory("Month of Year", 1, 12, false) );

        return  seeds;
    }

    private static RecurringPointOfTimeRequest SeedFactory(String description, int pointOfTimeStart, int pointOfTimeEnd, boolean isVariableEndPointOfTime){
        RecurringPointOfTimeRequest recurringPointOfTime = new RecurringPointOfTimeRequest();
        recurringPointOfTime.setDescription(description);
        recurringPointOfTime.setPointOfTimeStart(pointOfTimeStart);
        recurringPointOfTime.setPointOfTimeEnd(pointOfTimeEnd);
        recurringPointOfTime.setIsVariableEndPointOfTime(isVariableEndPointOfTime ? 1 : 0);

        return recurringPointOfTime;
    }
}
