package librerias;

public class habitacion {

        private String habitacion;
        private String tipo_habitacion;

        public habitacion(){}

        public habitacion(String a, String b){
                sethabitacion(a);
                settipohabitacion(b);
        }
        
        public String gethabitacion() {
                return habitacion;
        }
        
        public void sethabitacion(String a){
                this.habitacion=a;
        }
        
        public String gettipohabitacion() {
                return tipo_habitacion;
        }
        
        public void settipohabitacion(String b){
                this.tipo_habitacion=b;
        }
	
}
