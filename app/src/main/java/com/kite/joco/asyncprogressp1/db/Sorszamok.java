package com.kite.joco.asyncprogressp1.db;

import com.orm.SugarRecord;

/**
 * Created by Joco on 2016.01.04..
 */
public class Sorszamok extends SugarRecord {

        private String tosz;
        private String nyomtkod;
        private String sorszam;
        private Long id;

        public Sorszamok() {
        }

        public Sorszamok(Long id) {
            this.id = id;
        }

        public Sorszamok(Long id, String tosz, String nyomtkod) {
            this.id = id;
            this.tosz = tosz;
            this.nyomtkod = nyomtkod;
        }

        public String getTosz() {
            return tosz;
        }

        public void setTosz(String tosz) {
            this.tosz = tosz;
        }

        public String getNyomtkod() {
            return nyomtkod;
        }

        public void setNyomtkod(String nyomtkod) {
            this.nyomtkod = nyomtkod;
        }

        public String getSorszam() {
            return sorszam;
        }

        public void setSorszam(String sorszam) {
            this.sorszam = sorszam;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            hash += (id != null ? id.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object object) {
            // TODO: Warning - this method won't work in the case the id fields are not set
            if (!(object instanceof Sorszamok)) {
                return false;
            }
            Sorszamok other = (Sorszamok) object;
            if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "com.joco.nyomtserv2.Sorszamok[ id=" + id + " ]";
        }
}
