

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		//TODO Make sure this works
		for(AbstractPixel[] row : imageData ) {
			for(int i =0; i<row.length; i++) {
				row[i] = row[i].Negative();
			}
		}
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		imageData = toBWImage().imageData;
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		imageData = toGrayImage().imageData;
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		imageData = toColorImage().imageData;
	}
	
	public void convertToTransparentImage()
	{
		imageData = toTransparentImage().imageData;
	}
	
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] resized = new AbstractPixel[h][w];
		
		float wRatio = (float) (getWidth())/w;
		float hRatio = (float) (getHeight())/h;
		
		for(int i=0; i< h; i++) {
			for(int j=0; j<w; j++) {
				
				resized[i][j] = imageData[(int)(i*hRatio)][(int)(j*wRatio)];
				
			}
		}
		imageData = resized;
		height = h;
		width = w;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0)
	{
		if(row0 < 0 || col0 < 0)
			throw new IllegalArgumentException();
		
		for(int i=row0; i<imageData.length && i<(pm.height + row0); i++) {
			for(int j=col0; j<imageData[i].length && j<(pm.width + col0); j++) {
				imageData[i][j] = pm.getPixel(i-row0, j-col0);
			}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		AbstractPixel[][] resized = new AbstractPixel[h][w];
		
		for(int i=0; i< h; i++) {
			for(int j=0; j<w; j++) {
				resized[i][j] = imageData[i][j];
			}
		}
		imageData = resized;
		height = h;
		width = w;		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		AbstractPixel[][] translated = new AbstractPixel[height][width];
		
		for(AbstractPixel[] row : translated) {
			for(int i=0; i<row.length; i++) {
				row[i] = new BWPixel();
			}
		}
		
		for(int i=0; i<(translated.length - colOffset); i++) {
			for(int j=0; j<(translated[i].length - rowOffset); j++) {
				translated[i+rowOffset][j+colOffset] = imageData[i][j]; 
			}
		}
		
		imageData = translated;
		
	}
	
}
