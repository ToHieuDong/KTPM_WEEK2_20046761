package com.example.week2;

import java.io.File;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

public class ListClassesExample {
	public static void listClasses(File projectDir) {
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, 
		file) -> {
			System.out.println(path);
			System.out.println(Strings.repeat("=", path.length()));
			try {
				new VoidVisitorAdapter<Object>() {
					//Các package trong dự án phải theo mẫu: com.companyname.* (*:tên bất kỳ)
					@Override
					public void visit(PackageDeclaration n, Object arg) {
						super.visit(n, arg);

						if (!n.getNameAsString().startsWith("com.example.week2.parser"))
							System.out.println("Mau: com.example.week2.parser.*");
					}

					//Mỗi lớp phải có một comment mô tả cho lớp. Trong comment đó phải có ngày tạo (created-date) và author.
					//Tất cả các hằng số phải là chữ viết hoa và phải nằm trong một interface.
					@Override
					public void visit(ClassOrInterfaceDeclaration n, Object arg) {
						super.visit(n, arg);

						if (!n.getComment().isPresent()) {
							System.out.println("Invalid: Moi lop phai co mot comment mo ta cho lop" );
						}

						if (!checkClass(n.getNameAsString())) {
							System.out.println("Field Invalid: Viet hoa");
						}
					}

//					@Override
//					public void visit(MethodDeclaration n, Object arg) {
//						// TODO Auto-generated method stub
//						super.visit(n, arg);
//						System.out.println(" [L " + n.getBegin() + "] " + n.getDeclarationAsString());
//					}








				}.visit(StaticJavaParser.parse(file), null);
				System.out.println("-----"); // empty line
			} catch (Exception e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);
	}
	
	public static void main(String[] args) {
		File projectDir = new File("D:\\IUH_20046761\\HK8\\KTPM\\WSJAVA\\WEEK2\\src\\main\\java");
		listClasses(projectDir);
	}
	
	public static boolean checkClass (String name) {
		if ( name.charAt(0) >= 'A' && name.charAt(0) <= 'Z' ) 
			return true;
		return false;
	}
	
	
}

