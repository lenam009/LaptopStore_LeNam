interface IProps {
    dashBoard: IDashBoard | undefined;
}

function DashboardPage({ dashBoard }: IProps) {
    return (
        <main>
            <div className="container-fluid px-4">
                <h1 className="mt-4">Dashboard</h1>
                <ol className="breadcrumb mb-4">
                    <li className="breadcrumb-item active">Thống kê</li>
                </ol>
                <div className="row">
                    <div className="col-xl-4 col-md-6">
                        <div className="card bg-primary text-white mb-4">
                            <div className="card-body">
                                Count User({dashBoard?.countUser})
                            </div>
                            <div className="card-footer d-flex align-items-center justify-content-between">
                                <a className="small text-white stretched-link" href="#">
                                    View Details
                                </a>
                                <div className="small text-white">
                                    <i className="fas fa-angle-right"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-4 col-md-6">
                        <div className="card bg-warning text-white mb-4">
                            <div className="card-body">
                                Count Product({dashBoard?.countProduct})
                            </div>
                            <div className="card-footer d-flex align-items-center justify-content-between">
                                <a className="small text-white stretched-link" href="#">
                                    View Details
                                </a>
                                <div className="small text-white">
                                    <i className="fas fa-angle-right"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="col-xl-4 col-md-6">
                        <div className="card bg-success text-white mb-4">
                            <div className="card-body">
                                Count Order({dashBoard?.countOrder})
                            </div>
                            <div className="card-footer d-flex align-items-center justify-content-between">
                                <a className="small text-white stretched-link" href="#">
                                    View Details
                                </a>
                                <div className="small text-white">
                                    <i className="fas fa-angle-right"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
}

export default DashboardPage;
