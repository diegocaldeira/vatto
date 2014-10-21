package com.aoks.utils.generation;

import java.util.HashSet;
import java.util.Set;

public class ClassRep {

	private String pack;
	private Set<String> imports = new HashSet<String>();
	private String name;
	private Set<String> extendz = new HashSet<String>();
	private Set<String> implementz = new HashSet<String>();
	
	private Set<AnnotationRep> annotations = new HashSet<AnnotationRep>();
	private Set<FieldRep> fields = new HashSet<FieldRep>();
	private Set<MethodRep> methods = new HashSet<MethodRep>();
	
	public void addJavabeanProperty(String name, String type){
		
		FieldRep rep = new FieldRep();
		rep.setName(name);
		rep.setType(type);
		rep.setVisibility("private");
		
	}
	
	
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void addImport(String importz) {
		if (importz.split("\\.").length == 1)
			return;
		if (importz.startsWith("java.lang"))
			return;
		imports.add(importz);
	}
	public Set<String> getImports() {
		return imports;
	}
	public void addAnnotation(AnnotationRep annotation){
		addImport(annotation.getName());
		this.annotations.add(annotation);
	}
	public Set<AnnotationRep> getAnnotations() {
		return annotations;
	}
	public void addExtendz(String extendz) {
		this.extendz.add(extendz);
	}
	public void addImplements(String toImplement) {
		addImport(toImplement);
		String[] split = toImplement.split("\\.");
		implementz.add(split[split.length-1]);
	}
	public Set<String> getImplements() {
		return implementz;
	}
	public void addField(FieldRep field) {
		addImport(field.getType());
		fields.add(field);
	}
	public Set<FieldRep> getFields() {
		return fields;
	}
	public void addMethods(MethodRep method) {
		this.methods.add(method);
	}
	
}
