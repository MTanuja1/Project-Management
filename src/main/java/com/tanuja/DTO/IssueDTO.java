package com.tanuja.DTO;

import com.tanuja.model.Project;
import com.tanuja.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {

	private Long id;
	private String title;
	private String description;
	private String status;
	private Long projectId;
	private String priority;
	private LocalDate dueDate;
	private List<String> tags=new ArrayList<>();
	private Project project;
	private User assignee;
}
