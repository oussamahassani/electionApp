import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { DatePipe } from '@angular/common';

import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';  // <-- Make sure to import this

import { authIntercepterProvider } from './services/auth.intercepter';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './pages/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './pages/register/register.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { VoterDetailsComponent } from './pages/admin/voter/voter-details/voter-details.component';
import { FilterPipePipe } from './pipe/filter-pipe.pipe';
import { ViewSingleVoterComponent } from './pages/admin/voter/view-single-voter/view-single-voter.component';
import { VoterValidationComponent } from './pages/admin/voter/voter-validation/voter-validation.component';
import { SingleVoterValidationComponent } from './pages/admin/voter/single-voter-validation/single-voter-validation.component';
import { CandidateListComponent } from './pages/admin/candidate/candidate-list/candidate-list.component';
import { NewCandidateComponent } from './pages/admin/candidate/new-candidate/new-candidate.component';
import { UpdateCandidateComponent } from './pages/admin/candidate/update-candidate/update-candidate.component';
import { ElectionListComponent } from './pages/admin/election/election-list/election-list.component';
import { NewElectionComponent } from './pages/admin/election/new-election/new-election.component';
import { UpdateElectionComponent } from './pages/admin/election/update-election/update-election.component';
import { ViewCandidateComponent } from './pages/admin/candidate/view-candidate/view-candidate.component';
import { VoterDashboardComponent } from './pages/voter/voter-dashboard/voter-dashboard.component';
import { UserSidebarComponent } from './components/user-sidebar/user-sidebar.component';
import { VoterElectionComponent } from './pages/voter/voter-election/voter-election.component';
import { VoterHomeComponent } from './pages/voter/voter-home/voter-home.component';
import { VoterElectionPanelComponent } from './pages/voter/voter-election-panel/voter-election-panel.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ElectionResultComponent } from './pages/election-result/election-result.component';
import { AcceuilComponent } from './pages/acceuil/acceuil.component'
import { FooterComponent } from './components/footer/footer.component'
import { ContactUsComponent } from './pages/contact-us/contact-us.component'
import { NavbarWebComponent } from './components/navbarWeb/navbarWeb.component'
import { AdminDashbordComponent } from './pages/admin/admindashboard/admin-dashbord.component'
import { ElectionDashbordComponent } from './pages/admin/election/election-details/election-dashbord.component';
import { AddvoterComponent } from './pages/admin/add-voter/add-voter.component'
import { sendSmsvoterComponent } from './pages/admin/sendSms-voter/sendSms-voter.component'
import { ForgetPasswordComponent } from './pages/forgetPassword/forgetPassword.component'
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    SidebarComponent,
    HomeComponent,
    VoterDetailsComponent,
    FilterPipePipe,
    ViewSingleVoterComponent,
    VoterValidationComponent,
    SingleVoterValidationComponent,
    CandidateListComponent,
    NewCandidateComponent,
    UpdateCandidateComponent,
    ElectionListComponent,
    NewElectionComponent,
    UpdateElectionComponent,
    ViewCandidateComponent,
    VoterDashboardComponent,
    UserSidebarComponent,
    VoterElectionComponent,
    VoterHomeComponent,
    VoterElectionPanelComponent,
    ProfileComponent,
    ElectionResultComponent,
    AcceuilComponent,
    FooterComponent,
    ContactUsComponent,
    NavbarWebComponent,
    AdminDashbordComponent,
    ElectionDashbordComponent,
    AddvoterComponent,
    sendSmsvoterComponent,
    ForgetPasswordComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [authIntercepterProvider, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
