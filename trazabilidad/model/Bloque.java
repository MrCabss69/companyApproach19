package trazabilidad.model;

public class Bloque implements Serializable{
    private String hashPrevio;
    private int tipoBloque;
    private int numBloque;
    private int codLote;
    DatosContainer datos;

    //Constructor por defecto. Asignar tal cual
    //TODO anton
    public Bloque(String hashPrevio, int tipoBloque, int numBloque, int codLote, DatosContainer datos){
        
    }

    //Getters para todos los campos
    //TODO anton

    public String getHashCode() {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
            //Applies sha256 to our input, 
            this.setTimeStamp();
			String input=this.toBLOBString();
			byte[] hash = digest.digest(input.getBytes());	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private String toBLOBString() throws IOException {
		Serializable o = this;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
    }

    private void setTimeStamp() {
		this.timeStamp=System.currentTimeMillis();
	}
}