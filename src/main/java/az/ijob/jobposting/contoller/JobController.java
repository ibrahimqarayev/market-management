package az.ijob.jobposting.contoller;

import az.ijob.jobposting.dto.JobDto;
import az.ijob.jobposting.model.Job;
import az.ijob.jobposting.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/companies")
    public String companies() {
        return "companies";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/job-detail")
    public String jobDetail() {
        return "job-detail";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        List<JobDto> jobs = jobService.findAllJob();
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/jobs/{jobId}")
    public String jobDetail(@PathVariable Long jobId, Model model) {
        JobDto job = jobService.findById(jobId);
        model.addAttribute("job", job);
        return "job-detail";
    }

    @GetMapping("/jobs/new")
    public String createJobForm(Model model) {
        Job job = new Job();
        model.addAttribute("job", job);
        return "jobs-add";
    }


    @PostMapping("/jobs/new")
    public String addJob(@ModelAttribute("job") Job job) {
        jobService.addJob(job);
        return "redirect:/jobs";
    }

    @PostMapping("/jobs/{jobId}/delete")
    public String deleteJob(@PathVariable("jobId") Long jobId) {
        jobService.deleteById(jobId);
        return "redirect:/jobs";
    }

//    @GetMapping("/categories/{category}")
//    public String deleteJob(@PathVariable("category") String category, Model model) {
//        List<JobDto> jobs = jobService.findByCategory(category);
//        model.addAttribute("jobs", jobs);
//        return "jobs";
//    }

    @GetMapping("/jobs/search")
    public String searchJob(@RequestParam(name = "query") String query, Model model) {
        List<JobDto> jobs = jobService.searchJob(query);
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @GetMapping("/jobs/{jobId}/edit")
    public String editJobForm(@PathVariable("jobId") Long jobId, Model model) {
        JobDto job = jobService.findById(jobId);
        model.addAttribute("job", job);
        return "jobs-edit";
    }


    @PostMapping("/jobs/{jobId}/edit")
    public String updateJob(@PathVariable("jobId") Long jobId, @ModelAttribute("job") JobDto job) {
        job.setId(jobId);
        jobService.updateJob(job);
        return "redirect:jobs";
    }


}
