import { Component, OnInit, ElementRef } from '@angular/core';
import { DashbordService } from '../../../services/dashbord.service';
import Chart from 'chart.js/auto'

@Component({
    selector: 'app-admin-dashbord',
    templateUrl: './admin-dashbord.component.html',
    styleUrls: ['./admin-dashbord.component.css']
})
export class AdminDashbordComponent implements OnInit {
    private canvas: any;
    private ctx: any;
    private chart: any;
    labelcoutUserByDate: any;
    datacoutUserByDate: any;
    data: any = {
        counttransporteur: '0',
        acceptedColisPayed: '0',
        acceptedColisReturn: '0',
        nonAcceptedColis: '0',
        PendingElection: '0',
        acceptedColis: '0',
        OpenElection: '0'
    };
    constructor(
        private el: ElementRef,
        private dashbordService: DashbordService,
    ) { }

    ngOnInit(): void {
        this.dashbordService.getDashbordInfo().subscribe(res => {
            this.data = res
            let datacoutUserByDate = res.countColisByDate.map((el: any) => "" + el[2]);
            let labelcoutUserByDate = res.countColisByDate.map((el: any) => el[0] + "-" + el[1]);
            this.datacoutUserByDate = datacoutUserByDate;
            this.labelcoutUserByDate = labelcoutUserByDate;
            this.dochart(labelcoutUserByDate, datacoutUserByDate)
        }
        )
    }
    dochart(label: any, data: any) {
        this.canvas = this.el.nativeElement.querySelector('#canvas');

        this.ctx = this.canvas.getContext('2d');

        this.chart = new Chart(this.ctx, {
            type: 'bar',
            data: {
                labels: label,
                datasets: [{
                    label: 'User by Created Date',
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

}