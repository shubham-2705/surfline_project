package com.surfline.surflinegh.models.response;

import java.util.ArrayList;

/**
 * Created by shubhamlamba on 21/03/17.
 */

public class BalanceResponseModel extends BaseResponseModel {

    private ArrayList<BalanceData> balanceData;

    public ArrayList<BalanceData> getBalanceData() {
        return balanceData;
    }

    public void setBalanceData(ArrayList<BalanceData> balanceData) {
        this.balanceData = balanceData;
    }

    public class BalanceData
    {
        private String balanceType;
        private String balanceUnits;
        private String balanceExpiry;

        public String getBalanceExpiry() {
            return balanceExpiry;
        }

        public void setBalanceExpiry(String balanceExpiry) {
            this.balanceExpiry = balanceExpiry;
        }

        public String getBalanceType() {
            return balanceType;
        }

        public void setBalanceType(String balanceType) {
            this.balanceType = balanceType;
        }

        public String getBalanceUnits() {
            return balanceUnits;
        }

        public void setBalanceUnits(String balanceUnits) {
            this.balanceUnits = balanceUnits;
        }
    }
}
