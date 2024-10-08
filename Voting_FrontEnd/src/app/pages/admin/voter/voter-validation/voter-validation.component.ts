import { Component, OnInit } from '@angular/core';
import { VoterService } from 'src/app/services/voters/voter.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-voter-validation',
  templateUrl: './voter-validation.component.html',
  styleUrls: ['./voter-validation.component.css']
})
export class VoterValidationComponent implements OnInit {

  voters: any = []
  filterTerm: any;
  user: any = [];
  constructor(private _voter: VoterService) { }

  ngOnInit(): void {
    this._voter.getValidationVoters().subscribe(
      (data) => {
        this.voters = data;
        let user = this.voters.map((el: any) => {
          return {
            ...el.user,
            verification_id: el.verification_id
          }
        }
        );
        this.user = user
      },
      (error) => {
        Swal.fire("Error !!", "Error in loading data !", "error")
      }
    )
  }

}
