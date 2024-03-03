package com.ssb.board.action;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class ReviewFileNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File file) {
		String name = file.getName();
		String ext = "";
		
		int dot = name.lastIndexOf("."); // 입력값이 문자열에 없을 때 리턴값 -1		
		if(dot != -1) {
			ext = name.substring(dot);
		} else {
			ext = "";
		}
		
		String str = "" + UUID.randomUUID();
		
		// getParent() => 부모 경로에 대한 경로명을 문자열로 넘겨줌
		File renameFile = new File(file.getParent(), str + ext);
		
		return renameFile;
	}

}