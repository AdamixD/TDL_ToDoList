package com.example.myBackend.services;


import com.example.myBackend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBackend.models.Team;
import com.example.myBackend.models.ApplicationUser;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    private ApplicationUserService applicationUserService;

    @Autowired
    public TeamService(TeamRepository teamRepository, ApplicationUserService applicationUserService) {
        this.teamRepository = teamRepository;
        this.applicationUserService = applicationUserService;
    }

    public Optional<Team> getTeamByName(String name) {
        return teamRepository.findTeamByName(name);
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }

    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }

    public void saveTeam(Team team, ApplicationUser teamLeader){
        List<ApplicationUser> users = team.getTeamMembers();
        users.add(applicationUserService.loadApplicationUserById(teamLeader.getId()).get());
        team.setTeamMembers(users);
        teamRepository.save(team);
    }

    public List<Team> getTeamsTeamLeader(ApplicationUser teamLeader){
        return teamRepository.findTeamByTeamLeader(teamLeader);
    }
}
