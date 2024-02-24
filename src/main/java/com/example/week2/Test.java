package com.example.week2;

import java.io.File;

public class Test {

	public static void main (String[] args) {
		File projectDir = new File("D:\\IUH_20046761\\HK8\\KTPM\\WSJAVA\\WEEK2\\src\\main\\java\\com\\example\\week2\\parser");
		new  DirExplorer((level,  path,  file)  ->  path.endsWith(".java"),  (level,  path, 
		file) -> {
		System.out.println(path);
		}).explore(projectDir);
	}

}
