function HomepageUser() {
    return (
        <div className="tab-className text-center pt-3">
            <div className="row g-4">
                <div className="col-lg-4 text-start">
                    <h1>Products</h1>
                </div>
                <div className="col-lg-8 text-end">
                    <ul className="nav nav-pills d-inline-flex text-center mb-5">
                        <li className="nav-item">
                            <a
                                className="d-flex m-2 py-2 bg-light rounded-pill active"
                                data-bs-toggle="pill"
                                href="#tab-1"
                            >
                                <span className="text-dark" style={{ width: '130px' }}>
                                    All Products
                                </span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div className="tab-content">
                <div id="tab-1" className="tab-pane fade show p-0 active">
                    <div className="row g-4">
                        <div className="col-lg-12">
                            <div className="row g-4">
                                {/* <!-- Loop --> */}
                                {/* <c:forEach var="product" items="${products}">
                                <div className="col-md-6 col-lg-4 col-xl-3">
                                    <div className="rounded position-relative fruite-item">
                                        <div className="fruite-img">
                                            <img
                                                src="/images/product/${product.image}"
                                                className="img-fluid w-100 rounded-top"
                                                alt=""
                                            />
                                        </div>
                                        <div
                                            className="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                            style="top: 10px; left: 10px"
                                        >
                                            ${product.name}
                                        </div>
                                        <div className="p-4 border border-secondary border-top-0 rounded-bottom">
                                            <h5>
                                                <a href="/product/${product.id}">
                                                    ${product.name}
                                                </a>
                                            </h5>

                                            <p>${product.shortDesc}</p>

                                            <div className="d-flex justify-content-center flex-lg-wrap flex-column">
                                                <p className="text-dark fs-5 fw-bold mb-4">
                                                    <fmt:formatNumber
                                                        type="number"
                                                        value=" ${product.price}"
                                                    />
                                                    đ
                                                </p>
                                                <form
                                                    method="post"
                                                    action="/add-product-to-cart/${product.id}"
                                                >
                                                    <button
                                                        href="#"
                                                        className="btn border border-secondary rounded-pill px-3 text-primary"
                                                    >
                                                        <i className="fa fa-shopping-bag me-2 text-primary"></i>{' '}
                                                        Add to cart
                                                    </button>
                                                    <input
                                                        type="hidden"
                                                        name="${_csrf.parameterName}"
                                                        value="${_csrf.token}"
                                                    />
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach> */}

                                {[...Array(10)].map((e, i) => (
                                    <div className="col-md-6 col-lg-4 col-xl-3">
                                        <div className="rounded position-relative fruite-item">
                                            <div className="fruite-img">
                                                <img
                                                    src="/images/product/${product.image}"
                                                    className="img-fluid w-100 rounded-top"
                                                    alt=""
                                                />
                                            </div>
                                            <div
                                                className="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                style={{ top: '10px', left: '10px' }}
                                            >
                                                Product name
                                            </div>
                                            <div className="p-4 border border-secondary border-top-0 rounded-bottom">
                                                <h5>
                                                    <a href="#">Link product name</a>
                                                </h5>

                                                <p>short desc</p>

                                                <div className="d-flex justify-content-center flex-lg-wrap flex-column">
                                                    <p className="text-dark fs-5 fw-bold mb-4">
                                                        100 đ
                                                    </p>
                                                    <form
                                                        method="post"
                                                        action="/add-product-to-cart/${product.id}"
                                                    >
                                                        <button className="btn border border-secondary rounded-pill px-3 text-primary">
                                                            <i className="fa fa-shopping-bag me-2 text-primary"></i>{' '}
                                                            Add to cart
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default HomepageUser;
