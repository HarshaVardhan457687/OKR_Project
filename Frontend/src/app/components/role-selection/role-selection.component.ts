import { Component } from '@angular/core';

@Component({
  selector: 'app-role-selection',
  templateUrl: './role-selection.component.html',
  styleUrls: ['./role-selection.component.css']
})
export class RoleSelectionComponent {
  roles = [
    {
      title: 'Project Manager',
      description: "Lead and oversee your project's activities, manage resources, and ensure project success",
      icon: 'manage_accounts',
      action: 'Continue as Manager',
      buttonClass: 'manager-button',
      iconClass: 'manager-icon'
    },
    {
      title: 'Team Leader',
      description: "Guide and support your team's activities, facilitate collaboration, and drive team performance",
      icon: 'supervisor_account',
      action: 'Continue as Team Leader',
      buttonClass: 'leader-button',
      iconClass: 'leader-icon'
    },
    {
      title: 'Project Member',
      description: 'Collaborate effectively with your team, contribute to project goals, and deliver quality work',
      icon: 'person',
      action: 'Continue as Member',
      buttonClass: 'member-button',
      iconClass: 'member-icon'
    }
  ];

  selectRole(role: string) {
    console.log(`Selected role: ${role}`);
    // Add your role selection logic here
  }
} 