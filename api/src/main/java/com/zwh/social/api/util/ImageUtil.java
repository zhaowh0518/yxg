package com.zwh.social.api.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.raster.JimiRasterImage;

public class ImageUtil {

	public static byte[] cutImage(InputStream stream, String extName,
			int width, int hight, String contentType) throws Exception {

		/**
		 * Random rand = new Random(); String filepath = "D:" + File.separator +
		 * rand.nextInt(100) + "." + extName; ImageIO.write(image1, extName, new
		 * File(filepath)); InputStream inout = new
		 * ByteArrayInputStream(os.toByteArray()); BufferedImage image1 =
		 * ImageIO.read(inout);
		 **/
		// 裁剪图片
		BufferedImage bimage = ImageIO.read(stream);
		int h = bimage.getHeight();
		int w = bimage.getWidth();
		int c = 0;
		if (w > h) {
			c = (w - h) / 2;
			bimage = bimage.getSubimage(c, 0, h, h);
		} else {
			c = (h - w) / 2;
			bimage = bimage.getSubimage(0, c, w, w);
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bimage, extName, os);
		byte[] inbyte = os.toByteArray();
		// 压缩图片
		byte[] retbytearray = zoomImageNew(inbyte, extName, width, hight,
				contentType);
		return retbytearray;
	}

	

	public static byte[] zoomImageNew(byte[] inbyte, String extName, int width,
			int hight, String contentType) throws Exception {
		byte[] ab = resizeImage(inbyte, width, extName, contentType);
		return ab;
	}

	public static final byte[] input2byte(InputStream inStream)
			throws Exception {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	/**
	 * resize the image in byte stream(format: [in]GIF, JPG; [out]JPG)
	 * 
	 * @param in
	 *            - the binary stream of the original picture in GIF or JPG
	 * @param maxDim
	 *            - the bigger one between height and width after the picture is
	 *            resized
	 * @return the binary stream of the resized picture in JPG
	 */
	public static byte[] resizeImage(byte[] in, int maxDim, String extName,
			String contentType) {
		try {
			Image inImage = Toolkit.getDefaultToolkit().createImage(in);
			ImageIcon inImageIcon = new ImageIcon(in);

			int imh = inImageIcon.getIconHeight();
			int imw = inImageIcon.getIconWidth();
			double scale;
			int scaledW = 0;
			int scaledH = 0;

			if (imh <= maxDim && imw <= maxDim) {
				scale = 1;
				scaledW = 230;
				scaledH = 230;
			} else if (imh > imw) {
				scaledH = 230;
				scale = (double) imw / imh;
				scaledW = (int) (scale * scaledH);
				// if (scaledW < 230)
				// scaledW = 230;
				// scale = (double) maxDim / (double) imh;
			} else {
				scaledW = 230;
				scale = (double) imh / imw;
				scaledH = (int) (scale * scaledW);
				// if (scaledH < 230)
				// scaledH = 230;
				// scale = (double) maxDim / (double) imw;
			}
			// scaledW = (int) (scale * imw);
			// scaledH= (int) (scale * imh);

			Image img = inImage.getScaledInstance(scaledW, scaledH,
					Image.SCALE_SMOOTH);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			JimiRasterImage raster = Jimi.createRasterImage(img.getSource());
			// --java.io.ByteArrayOutputStream
			Jimi.putImage(contentType, raster, outStream);
			outStream.flush();
			outStream.close();
			return outStream.toByteArray();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		String picPath = "E:/test.jpg";
		String destPath = "E:/result4.jpg";
		File file = new File(picPath);
		InputStream stream = new FileInputStream(file);
		String extName = "jpg";
		/**
	    byte[] out=cutImage(stream, extName, 230, 230, "image/jpeg");
		//byte[] byt = new byte[out.length];
	    InputStream ins = new ByteArrayInputStream(out);
		BufferedImage bimage = ImageIO.read(ins);
		File res=new File(destPath);
		ImageIO.write(bimage, extName, res);
		**/
		
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(extName);
		ImageReader reader = (ImageReader) iterator.next();
		//InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(stream);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect =null;
		int h =reader.getHeight(0) ;
		int w = reader.getWidth(0);
		int c = 0;
		if (w > h) {
			c = (w - h) / 2;
			rect=new Rectangle(c, 0, w, h);
		} else {
			c = (h - w) / 2;
			rect=new Rectangle(0, c, w, h);
		}
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
	
		
		if(w != 230){ //被拉伸或者缩小过
			bi.flush();
			BufferedImage newImage = new BufferedImage(230,230,bi.getType());
			Graphics g = newImage.getGraphics();
			g.drawImage(bi,0,0,230,230,null,null);
			g.dispose();
			ImageIO.write(newImage, "jpg", new File(destPath));
		}else{
			ImageIO.write(bi, "jpg", new File(destPath));
		}


	}

	public static String getFileTypeByextName(String extName) {
		String contentType = null;
		String arg[] = Jimi.getDecoderTypes();
		for (int i = 0; i < arg.length; i++) {
			System.out.println("===" + arg[i]);
		}
		if ("jpeg".equalsIgnoreCase(extName) || "jpg".equalsIgnoreCase(extName)) {
			contentType = "image/jpeg";
		} else if ("PNG".equalsIgnoreCase(extName)) {
			contentType = "image/png";
		} else if ("BMP".equalsIgnoreCase(extName)) {
			contentType = "image/bmp";
		} else if ("GIF".equalsIgnoreCase(extName)) {
			contentType = "image/gif";
		}
		return contentType;
	}
	/**
	 * 获取图片的拍摄日期
	 * @param fileStream
	 * @return
	 */
	public static Date getShootDate(InputStream fileStream){
		Date shootDate = null;
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(fileStream);
			Iterable<Directory> list = metadata.getDirectories();
			for(Directory dir : list){
				if(dir.containsTag(ExifSubIFDDirectory.TAG_DATETIME)){
					shootDate = dir.getDate(ExifSubIFDDirectory.TAG_DATETIME);
				}
			}
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return shootDate;
	}
}
