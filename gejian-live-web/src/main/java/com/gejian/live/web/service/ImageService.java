package com.gejian.live.web.service;

import com.gejian.common.core.util.SpringContextHolder;
import com.gejian.live.common.dto.SnapshotInfo;
import com.gejian.live.common.enums.ImageLocation;
import com.gejian.live.web.config.ServerComponent;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static cn.hutool.core.img.ImgUtil.getImageOutputStream;
import static cn.hutool.core.img.ImgUtil.pressImage;
import static cn.hutool.core.img.ImgUtil.pressText;
import static cn.hutool.core.img.ImgUtil.read;

/**
 * @author ：lijianghuai
 * @date ：2021-09-26 11:11
 * @description：图片处理服务
 */

@Service
@Slf4j
public class ImageService {

	/**
	 * 添加文字水印
	 * @param imageFile 源文件
	 * @param destFile 目标文件
	 * @param pressText 水印文本
	 * @param color 水印颜色
	 * @param font 水印字体
	 * @param imageLocationType 水印位置
	 * @param alpha  透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(File imageFile, File destFile, String pressText, Color color, Font font, ImageLocation imageLocationType, float alpha) {
		press(read(imageFile), getImageOutputStream(destFile), pressText, color, font, imageLocationType, alpha);
	}

	/**
	 * 添加文字水印
	 * @param inputStream 源输入流
	 * @param outputStream 输出流
	 * @param pressText 水印文本
	 * @param color 水印颜色
	 * @param font 水印字体
	 * @param imageLocationType 水印位置
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(InputStream inputStream, OutputStream outputStream, String pressText, Color color, Font font, ImageLocation imageLocationType, float alpha) {
		press(read(inputStream), getImageOutputStream(outputStream), pressText, color, font, imageLocationType, alpha);
	}


	/**
	 * 添加文字水印
	 * @param image 源图片
	 * @param imageOutputStream 目标图片
	 * @param pressText 水印文本
	 * @param color 水印颜色
	 * @param font 水印字体
	 * @param imageLocationType 水印位置
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(BufferedImage image, ImageOutputStream imageOutputStream, String pressText, Color color, Font font, ImageLocation imageLocationType, float alpha) {
		pressLocaltion(image,imageLocationType,pressText,font).ifPresent(ints -> pressText(image, imageOutputStream, pressText, color, font, ints[0], ints[1], alpha));
	}

	/**
	 * 添加图片水印
	 * @param imageFile 源文件
	 * @param destFile 目标文件
	 * @param pressImg 水印图片
	 * @param imageLocationType 水印位置
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(File imageFile, File destFile, File pressImg, ImageLocation imageLocationType, float alpha) {
		press(read(imageFile), getImageOutputStream(destFile), read(pressImg), imageLocationType, alpha);
	}

	/**
	 * 添加图片水印
	 * @param inputStream 源文件
	 * @param outputStream 目标文件
	 * @param pressImg 水印图片
	 * @param imageLocationType 水印位置
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(InputStream inputStream, OutputStream outputStream, InputStream pressImg, ImageLocation imageLocationType, float alpha) {
		press(read(inputStream), getImageOutputStream(outputStream), read(pressImg),  imageLocationType, alpha);
	}

	/**
	 * 添加图片水印
	 * @param image 源图片
	 * @param outputStream 目标图片
	 * @param pressImg 水印图片
	 * @param imageLocationType 水印位置
	 * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public void press(BufferedImage image, ImageOutputStream outputStream, BufferedImage pressImg, ImageLocation imageLocationType, float alpha) {
		pressLocaltion(image,imageLocationType,pressImg).ifPresent(ints -> pressImage(image, outputStream, pressImg, ints[0], ints[1], alpha));
	}

	/**
	 * 直播间截图
	 * @param roomId 房间号
	 * @return
	 */
	public Optional<SnapshotInfo> snapShot(int roomId){
		ServerComponent serverComponent = SpringContextHolder.getBean(ServerComponent.class);
		String rtmpUrl = serverComponent.getRtmpUrl(roomId);
		byte[] bytes = null;
		try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtmpUrl)){
			grabber.setOption("rw_timeout" , "1000000");
			grabber.start();
			Frame frame;
			Java2DFrameConverter converter = new Java2DFrameConverter();
			if((frame = grabber.grabImage()) != null) {
				BufferedImage image = converter.convert(frame);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				try {
					ImageIO.write(image, "png", outputStream);
					bytes = outputStream.toByteArray();
				} catch (IOException e) {
					log.error("snapShot error...",e);
				}
			}
		} catch (FrameGrabber.Exception e) {
			log.error("snap shot error ..",e);
		}
		if(bytes == null || bytes.length == 0){
			return Optional.empty();
		}else{
			LocalDateTime now = LocalDateTime.now();
			String fileName = roomId + "-" + now.toEpochSecond(ZoneOffset.ofHours(8)) + ".png";
			return Optional.of(new SnapshotInfo(fileName,bytes,roomId,now));
		}
	}

	/**
	 * 水印相对位置，默认为中间位置。
	 * @param image 源图片
	 * @param imageLocationType 水印位置
	 * @param text 水印文字
	 * @param font 水印字体
	 * @return
	 */
	private Optional<int[]> pressLocaltion(BufferedImage image, ImageLocation imageLocationType, String text, Font font){
		FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
		int pressWidth = fm.stringWidth(text);
		int pressHeight = fm.getHeight();
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		switch (imageLocationType){
			case LEFT_TOP:
				return Optional.of(new int[]{ (pressWidth - imgWidth) / 2 , pressHeight - imgHeight / 2  });
			case RIGHT_TOP:
				return Optional.of(new int[]{(imgWidth - pressWidth ) / 2,pressHeight - imgHeight / 2});
			case LEFT_BOTTOM:
				return Optional.of(new int[]{ (pressWidth - imgWidth) / 2, imgHeight / 2 });
			case RIGHT_BOTTOM:
				return Optional.of(new int[]{(imgWidth - pressWidth ) / 2,imgHeight / 2});
			default:return Optional.of(new int[]{0,0});
		}
	}

	/**
	 * 水印相对位置，默认为中间位置。
	 * @param source 源图片
	 * @param imageLocationType 水印位置
	 * @param markImg 水印图片
	 * @return
	 */
	private Optional<int[]> pressLocaltion(BufferedImage source, ImageLocation imageLocationType, BufferedImage markImg){
		int pressWidth = markImg.getWidth();
		int pressHeight = markImg.getHeight();
		int imgWidth = source.getWidth();
		int imgHeight = source.getHeight();
		switch (imageLocationType){
			case LEFT_TOP:
				return Optional.of(new int[]{ (pressWidth - imgWidth) / 2 , (pressHeight - imgHeight) / 2 });
			case RIGHT_TOP:
				return Optional.of(new int[]{(imgWidth - pressWidth ) / 2, (pressHeight-imgHeight) / 2 });
			case LEFT_BOTTOM:
				return Optional.of(new int[]{ (pressWidth - imgWidth) / 2, (imgHeight - pressHeight) / 2 });
			case RIGHT_BOTTOM:
				return Optional.of(new int[]{(imgWidth - pressWidth ) / 2,(imgHeight - pressHeight) / 2});
			default:return Optional.of(new int[]{0,0});
		}
	}
}
