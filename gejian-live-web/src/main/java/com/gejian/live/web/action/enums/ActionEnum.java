package com.gejian.live.web.action.enums;

import com.gejian.live.web.action.params.ActionParams;
import com.gejian.live.web.action.params.CloseConnectionActionParams;
import com.gejian.live.web.action.params.ConnectionActionParams;
import com.gejian.live.web.action.params.DvrActionParams;
import com.gejian.live.web.action.params.HLSActionParams;
import com.gejian.live.web.action.params.PlayActionParams;
import com.gejian.live.web.action.params.PublishActionParams;
import com.gejian.live.web.action.params.StopPlayActionParams;
import com.gejian.live.web.action.params.UnPublishActionParams;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 11:39
 * @description：
 */
public enum ActionEnum {
	/**
	 * 连接
	 */
	ON_CONNECT {
		@Override
		public ActionParams params(){
			return new ConnectionActionParams();
		}
	},
	/**
	 * 断开连接
	 */
	ON_CLOSE{
		@Override
		public ActionParams params(){
			return new CloseConnectionActionParams();
		}
	},
	/**
	 * 推流
	 */
	ON_PUBLISH {
		@Override
		public ActionParams params(){
			return new PublishActionParams();
		}
	},
	/**
	 * 停止推流
	 */
	ON_UNPUBLISH {
		@Override
		public ActionParams params(){
			return new UnPublishActionParams();
		}
	},
	/**
	 * 播放
	 */
	ON_PLAY{
		@Override
		public ActionParams params(){
			return new PlayActionParams();
		}
	},
	/**
	 * 停止播放
	 */
	ON_STOP {
		@Override
		public ActionParams params(){
			return new StopPlayActionParams();
		}
	},
	/**
	 * 录像
	 */
	ON_DVR {
		@Override
		public ActionParams params(){
			return new DvrActionParams();
		}
	},
	/**
	 * HLS切片
	 */
	ON_HLS {
		@Override
		public ActionParams params(){
			return new HLSActionParams();
		}
	};

	public abstract ActionParams params();
}
