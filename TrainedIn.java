import java.sql.*;
public class TrainedIn {

        private int physicianId;
        private String treatment;
         private Date certificationDate;
        private Date certificationExpires;

        public TrainedIn(int physicianId, String treatment, Date certificationDate, Date certificationExpires) {
            this.physicianId = physicianId;
            this.treatment = treatment;
            this.certificationDate = certificationDate;
            this.certificationExpires = certificationExpires;
        }

        public int getPhysicianId() {
            return physicianId;
        }

        public void setPhysicianId(int physicianId) {
            this.physicianId = physicianId;
        }

        public String getTreatment() {
            return treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }

        public Date getCertificationDate() {
            return certificationDate;
        }

        public void setCertificationDate(Date certificationDate) {
            this.certificationDate = certificationDate;
        }

        public Date getCertificationExpires() {
            return certificationExpires;
        }

        public void setCertificationExpires(Date certificationExpires) {
            this.certificationExpires = certificationExpires;
        }
}
