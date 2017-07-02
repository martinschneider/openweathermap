package at.mschneider.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.repository.CityRepository;
import at.mschneider.weatherapp.repository.WeatherRepository;

@Controller
@RequestMapping("/city")
public class CityController {

	private CityRepository cityRepository;
	private WeatherRepository weatherRepository;

	@Autowired
	public CityController(CityRepository ciyRepository, WeatherRepository weatherRepository)
	{
		this.cityRepository = ciyRepository;
		this.weatherRepository = weatherRepository;
	}

	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("cities", cityRepository.findAll());
		return "city";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCity(@ModelAttribute("city") City city) {
		cityRepository.saveAndFlush(city);
		return "redirect:/city/list";
	}

	@RequestMapping(value = "/delete/{cityId}", method = RequestMethod.GET)
	public String deleteCity(@PathVariable Long cityId) {
		// alternatively make WeatherData a collection inside City and use CascadeType.REMOVE
		weatherRepository.deleteByCityId(cityId);
		cityRepository.delete(cityId);
		return "redirect:/city/list";
	}
}
