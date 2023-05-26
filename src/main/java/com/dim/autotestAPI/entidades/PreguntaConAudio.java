package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.ConAudio;

public class PreguntaConAudio extends PreguntaConID implements ConAudio{

	private String audioURL;
	
	@Override
	public String getaudioURL() {
		return audioURL;
	}

}
