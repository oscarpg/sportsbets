package com.rurallabs.sportsbets.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rurallabs.sportsbets.business.entities.Competition;
import com.rurallabs.sportsbets.business.services.CompetitionService;
import com.rurallabs.sportsbets.web.admin.beans.CompetitionBean;
import com.rurallabs.sportsbets.web.admin.beans.LangBean;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

	private static final String VIEW_BASE = "admin/competition/";

	@Autowired
	private CompetitionService competitionService;

	public CompetitionController() {
		super();
	}

	@RequestMapping("/list")
	public String list(final HttpServletRequest request, final ModelMap model) {
		final List<Competition> competitions = this.competitionService.findAll();
		model.addAttribute("allCompetitions", competitions);
		return VIEW_BASE + "list";
	}

	@RequestMapping("/manage")
	public String manage(@RequestParam(value = "id", required = false) final Long id, final ModelMap model) {

		final CompetitionBean competitionBean = new CompetitionBean();

		if (id != null) {
			final Competition competition = this.competitionService.find(id);
			competitionBean.setId(competition.getId());
			competitionBean.setName(competition.getName());
			competitionBean.setActive(competition.isActive());
			competitionBean.getNamesByLang().clear();
			competitionBean.getNamesByLang().addAll(LangBean.listFromMap(competition.getNamesByLang()));
		}

		model.addAttribute("competition", competitionBean);
		return VIEW_BASE + "manage";

	}

	@RequestMapping("/save")
	public String save(final CompetitionBean competitionBean, final BindingResult bindingResult) {

		this.competitionService.save(competitionBean.getId(), competitionBean.getName(),
				LangBean.mapFromList(competitionBean.getNamesByLang()), competitionBean.isActive());

		return "redirect:list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "id") final Long id) {

		this.competitionService.delete(id);
		return "redirect:list";

	}

}
