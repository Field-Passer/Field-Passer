package com.example.fieldpasserbe.admin.controller;

import com.example.fieldpasserbe.admin.dto.BlindRequestDTO;
import com.example.fieldpasserbe.admin.dto.PeriodRequestDTO;
import com.example.fieldpasserbe.admin.dto.PostResponseDTO;
import com.example.fieldpasserbe.admin.service.AdminLoginService;
import com.example.fieldpasserbe.admin.service.AdminManageService;
import com.example.fieldpasserbe.admin.service.AdminService;
import com.example.fieldpasserbe.admin.dto.AdminLoginRequestDTO;
import com.example.fieldpasserbe.admin.vo.*;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminLoginService adminLoginService;
    private final AdminService adminService;
    private final AdminManageService adminManageService;

    @PostMapping("/admin/auth/login")
    public AdminLoginVO adminLogin(@RequestBody AdminLoginRequestDTO adminLoginRequest, HttpSession session) throws Exception {
        return adminLoginService.adminLogin(adminLoginRequest, session);
    }

    @GetMapping("/admin/members")
    public MemberListVO lookUpMembers(@RequestParam(name = "page") int page) throws Exception {
        return adminService.lookUpmembers(page);
    }

    @PutMapping("/admin/promote")
    public SimpleVO promoteAdmin(@RequestBody Map<String, String> map) throws Exception {
        return adminManageService.promoteAdmin(map.get("email"));
    }

    @GetMapping("/admin/members/{memberId}")
    public MemberVO memberDetail(@PathVariable int memberId) throws Exception {
        return adminService.memberDetail(memberId);
    }

    @GetMapping("/admin/members/new")
    public PeriodMemberVO checkNewMember(PeriodRequestDTO period, int page) throws Exception {
        return adminService.checkNewMember(period, page);
    }

    @GetMapping("/admin/members/punishment")
    public PunishVO lookupPunishment(int page) throws Exception {
        return adminService.lookUpPunishment(page);
    }

    @GetMapping("/admin/board/members/{memberId}")
    public PostVO postByID(@PathVariable int memberId, @RequestParam(name = "page") int page) throws Exception {
        return adminService.findPostsById(page, memberId);
    }

    @GetMapping("/admin/board")
    public PostVO lookupAllPosts(PeriodRequestDTO period, int page) throws Exception {
        return adminService.lookupAllPosts(period, page);
    }

    @GetMapping("/admin/board/new")
    public PeriodPostVO checkNewPosts(PeriodRequestDTO period, int page) throws Exception {
        return adminService.checkNewPosts(period, page);
    }

    @PutMapping("/admin/board/blind")
    public SimpleVO blindPost(@RequestBody BlindRequestDTO blind) {
        return adminManageService.blind(blind);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Objects> exception(Exception ex) {
        SimpleVO result = SimpleVO.builder()
                .resultCode(ex.getMessage())
                .build();
        return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
    }
}