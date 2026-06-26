package org.example.Model;

public class DangKyLopRequest {
        private String maSv;
        private String maLopHP;

        public DangKyLopRequest() {}

        public DangKyLopRequest(String maSv, String maLopHP) {
            this.maSv = maSv;
            this.maLopHP = maLopHP;
        }

        public String getMaSv() {
            return maSv;
        }

        public void setMaSv(String maSv) {
            this.maSv = maSv;
        }

        public String getMaLopHP() {
            return maLopHP;
        }

        public void setMaLopHP(String maLopHP) {
            this.maLopHP = maLopHP;
        }
}
