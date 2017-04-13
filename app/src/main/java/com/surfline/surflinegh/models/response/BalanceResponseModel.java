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
        private String balanceTypeName;
        private String balanceUnits;
        private String balanceExpiry;
        private String balanceBucketType;

        public String getBalanceBucketType() {
            return balanceBucketType;
        }

        public void setBalanceBucketType(String balanceBucketType) {
            this.balanceBucketType = balanceBucketType;
        }

        public String getBalanceExpiry() {
            return balanceExpiry;
        }

        public void setBalanceExpiry(String balanceExpiry) {
            this.balanceExpiry = balanceExpiry;
        }

        public String getBalanceType() {
            return balanceTypeName;
        }

        public void setBalanceType(String balanceType) {
            this.balanceTypeName = balanceType;
        }

        public String getBalanceUnits() {
            return balanceUnits;
        }

        public void setBalanceUnits(String balanceUnits) {
            this.balanceUnits = balanceUnits;
        }
    }
}
