import { Component, OnInit, ElementRef } from '@angular/core';
import { DashbordService } from '../../../../services/dashbord.service';
import Chart from 'chart.js/auto'
import { ActivatedRoute } from '@angular/router';
import { ElectionService } from 'src/app/services/election/election.service';
import { DatePipe } from '@angular/common';

@Component({
    selector: 'app-election-dashbord',
    templateUrl: './election-dashbord.component.html',
    styleUrls: ['./election-dashbord.component.css']
})
export class ElectionDashbordComponent implements OnInit {
    private electionByCity: any;
    private ctx: any;
    private chart: any;
    private electionBygender: any;
    private ctx1: any;
    private chart1: any;
    labelcoutUserByDate: any;
    datacoutUserByDate: any;
    datacoutUserByGender: any;
    labelcoutUserByGender: any;
    eid: any;
    election: any;
    constructor(
        private el: ElementRef,
        private _election: ElectionService,
        private datePipe: DatePipe,
        private ActivatedRouter: ActivatedRoute,
        private dashbordService: DashbordService,
    ) { }

    ngOnInit(): void {
        this.eid = this.ActivatedRouter.snapshot.params['cid']
        this.dashbordService.getElectionDashbordInfo(this.eid).subscribe(res => {

            let datacoutUserByDate = res.countElectionByDate.map((el: any) => "" + el[3]);
            let labelcoutUserByDate = res.countElectionByDate.map((el: any) => el[0] + "-" + el[1]);
            this.datacoutUserByDate = datacoutUserByDate;
            this.labelcoutUserByDate = labelcoutUserByDate;
            this.dochart(labelcoutUserByDate, datacoutUserByDate)
            this.datacoutUserByGender = res.countElectionByGender.map((el: any) => "" + el[1]);
            this.labelcoutUserByGender = res.countElectionByGender.map((el: any) => el[0]);
            console.log(this.datacoutUserByGender, this.labelcoutUserByGender)
            this.dochartByGender(this.labelcoutUserByGender, this.datacoutUserByGender)

            let datacoutUserByGouvernerat = res.countElectionByGouvernerat.map((el: any) => "" + el[1]);
            let labelcoutUserByGouvernerat = res.countElectionByGouvernerat.map((el: any) => el[0]);

            this.dochartByGouverenrat(labelcoutUserByGouvernerat, datacoutUserByGouvernerat)
        }
        )

        this._election.getElectionData(this.eid).subscribe(
            (data: any) => {
                this.election = data


            }
        )
    }
    dochart(label: any, data: any) {
        this.electionByCity = this.el.nativeElement.querySelector('#electionByDate');

        this.ctx = this.electionByCity.getContext('2d');

        this.chart = new Chart(this.ctx, {
            type: 'line',
            data: {
                labels: label,
                datasets: [{
                    label: 'Election by Date',
                    data: data,

                    borderColor: [
                        'rgba(255, 99, 132, 1)',

                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {

                }
            }
        });
    }
    dochartByGender(label: any, data: any) {
        this.electionBygender = this.el.nativeElement.querySelector('#electionBygender');

        this.ctx1 = this.electionBygender.getContext('2d');

        this.chart1 = new Chart(this.ctx1, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: 'Election By gender ',
                    data: data,

                    borderColor: [
                        'rgba(255, 99, 132, 1)',

                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {

                }
            }
        });

    }
    dochartByGouverenrat(label: any, data: any) {
        this.electionByCity = this.el.nativeElement.querySelector('#electionByCity');

        this.ctx = this.electionByCity.getContext('2d');

        this.chart = new Chart(this.ctx, {
            type: 'line',
            data: {
                labels: label,
                datasets: [{
                    label: 'Election by city',
                    data: data,

                    borderColor: [
                        'rgba(255, 99, 132, 1)',

                    ],
                    backgroundColor: [
                        'rgba(0,0,255,1.0)',

                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {

                }
            }
        });
    }

    formattedDate(date: Date): string {
        return this.datePipe.transform(date, 'dd/MM/yyyy @ hh:mm a') || '';
    }
}