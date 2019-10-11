package com.cts.MicroService.client.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.MicroService.client.model.Notification;
import com.cts.MicroService.client.model.Training;
import com.cts.MicroService.client.service.TrainingService;


@RestController

public class TrainingController {
	@Autowired
	TrainingService trainingservice;
	@GetMapping("/list")
	public List<Training> display()
	{
	return trainingservice.display();	
	}
	@PostMapping("/add")
	public String addtechnology(@RequestBody Training training)
	{
	trainingservice.add(training);
	return "Added Successfully";
	}
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public Optional<Training> getlist(@PathVariable Long id) {
		return trainingservice.getBymentorId(id);
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public String deleteTraining(@PathVariable Long id) {
		trainingservice.delete(id);
		return "Deleted Successfully";
		
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String updatetraining(@RequestBody Training training) {
		 trainingservice.update(training);
		 return "Updated Successfully";
	}
	@RequestMapping(value = "/displ", method = RequestMethod.POST)
	public List<Training> display(@RequestBody List<Long> list) {
		return trainingservice.trainingdetails(list);
		
	}
	@RequestMapping(value="/progress/{id}",method=RequestMethod.GET)
	public List<Training> showprogress(@PathVariable Long id)
	{
		return trainingservice.showprogress(id);
	}
	@RequestMapping(value="/completed/{id}",method=RequestMethod.GET)
	public List<Training> showcompleted(@PathVariable Long id)
	{
		return trainingservice.showcompleted(id);
	}


@RequestMapping(value="/propose/{uid}/{mid}/{skillName}",method=RequestMethod.GET)
public void propose(@PathVariable Long uid,@PathVariable Long mid,@PathVariable String skillName)
{
	trainingservice.propose(uid,mid,skillName);
}
@RequestMapping(value="/propose/{id}",method=RequestMethod.GET)
public List<Notification> proposal(@PathVariable Long id)
{
	return trainingservice.proposal(id);}

@RequestMapping(value="/add/{uid}/{mid}/{skillName}/{status}",method=RequestMethod.GET)
public void proposetraining(@PathVariable Long uid,@PathVariable Long mid,@PathVariable String skillName,@PathVariable String status)
{
	trainingservice.addtraining(uid,mid,skillName,status);
}}