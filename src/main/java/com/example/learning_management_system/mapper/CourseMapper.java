package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.entity.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseRequestDTO courseRequestDTO);

//    @Mapping(source = "modules.youtubeLink", target = "modules.youtubeLinks")
    CourseResponseDTO toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateCourseFromDto(CourseRequestDTO dto, @MappingTarget Course entity);

}
