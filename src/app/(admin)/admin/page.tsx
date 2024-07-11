function DashboardAdminPage() {
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
                            <div className="card-body">Count User(3)</div>
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
                            <div className="card-body">Count Product(5)</div>
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
                            <div className="card-body">Count Order(7)</div>
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

export default DashboardAdminPage;
