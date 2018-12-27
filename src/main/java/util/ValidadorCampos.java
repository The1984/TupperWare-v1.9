package util;

public class ValidadorCampos 
{
	
	private static final String pattNombreApellido = "^[a-zA-ZÒ—·ÈÌÛ˙¡…Õ”⁄ ]{3,30}$";
	private static final String pattMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
											+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String pattCelular = "^[0-9-+/]{6,30}$";
	private static final String pattPrecio = "[0-9]*";
	
	public static boolean campoVacio(String campo) 
	{
		return campo.equals("");
	}
	
	public static boolean validarNombre(String nombre)
	{
		return nombre.matches(pattNombreApellido);
	}
	
	public static boolean validarApellido(String apellido)
	{
		return apellido.matches(pattNombreApellido);
	}
	
	public static boolean validarMail(String mail)
	{
		return mail.matches(pattMail);
	}
	
	public static boolean validarCelular(String celular)
	{
		return celular.matches(pattCelular);
	}
	
	public static boolean validarPrecio(String precio)
	{
		return precio.matches(pattPrecio);
	}
	
}