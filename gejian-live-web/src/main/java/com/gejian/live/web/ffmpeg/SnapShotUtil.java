package com.gejian.live.web.ffmpeg;

import com.gejian.common.core.util.SpringContextHolder;
import com.gejian.live.web.config.ServerComponent;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ：lijianghuai
 * @date ：2021-09-24 11:14
 * @description：截图工具
 */
@Slf4j
public class SnapShotUtil {

	/**
	 * 截图
	 * @param roomId 房间号
	 */
	public static Optional<SnapshotInfo> snapShot(int roomId) {
		String fileName = roomId + "-" + "snapShot.png";
		ServerComponent serverComponent = SpringContextHolder.getBean(ServerComponent.class);
		String rtmpUrl = serverComponent.getRtmpUrl(roomId);
		byte[] bytes = null;
		try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtmpUrl)){
			grabber.setOption("rw_timeout" , "1000000");
			grabber.start();
			Frame frame;
			Java2DFrameConverter converter=new Java2DFrameConverter();
			if((frame = grabber.grabImage()) != null) {
				BufferedImage image=converter.convert(frame);
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
		if(bytes == null){
			return Optional.empty();
		}else{
			return Optional.of(new SnapshotInfo(fileName,bytes,roomId, LocalDateTime.now()));
		}
	}

}
