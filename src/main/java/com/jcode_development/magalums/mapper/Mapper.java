package com.jcode_development.magalums.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
	
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return MODEL_MAPPER.map(origin, destination);
	}
	
	public static <O, D> List<D> parseObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<>();
		for (O o : origin) {
			destinationObjects.add(MODEL_MAPPER.map(o, destination));
		}
		return destinationObjects;
	}
}
